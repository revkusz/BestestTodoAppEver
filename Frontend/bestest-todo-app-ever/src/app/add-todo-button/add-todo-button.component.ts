import { Component, OnInit, Input } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CategoryService } from '../category-service.service';
import { AuthService } from '../auth.service';
import { Category } from 'src/models/Category';
import { TodoService } from '../todo.service';
import { EventEmitterService } from '../event-emitter.service';

@Component({
  selector: 'add-todo-button',
  templateUrl: './add-todo-button.component.html',
  styleUrls: ['./add-todo-button.component.css']
})
export class AddTodoButtonComponent implements OnInit {

  todoMessage: string;
  category: string;

  username: string;
  password: string;

  buttonText: string;

  categories: Category[];

  constructor(private modalService: NgbModal, private categoryService: CategoryService, 
              private authService: AuthService, private todoService: TodoService,
              private eventEmitter: EventEmitterService) {
                this.setButtonText();

                this.eventEmitter.buttonTextSubsVar = this.eventEmitter.invokeSetButtonText.subscribe(() => {
                  this.setButtonText();
                })
               }

  ngOnInit(): void {
  };
  
  open(content: any) {
    if (this.authService.isLoggedIn()) {

      const username = this.authService.getUserName();
      const token = this.authService.getToken();
  
      this.categoryService.getCategories(username, token).subscribe(data => {
        this.categories = data.map(cat => {
          return {
            name: cat.name,
            color: cat.color,
            id: cat.id
          }
        });
  
        this.category = this.categories[0].id;
  
      });
    } else {

    }
    
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  };

  isHidden() {
    return !localStorage.getItem('todoToken');
  };

  onSelectChange(event) {
    this.category = event.target.value;
  }

  postTodo() {
    if (!this.authService.isLoggedIn()) {
      return;
    }

    const token = this.authService.getToken();
    const username = this.authService.getUserName();

    this.todoService.postTodo(username, token, this.todoMessage, this.category).subscribe(() => {
      this.eventEmitter.onInvokeGetTodo();
      this.modalService.dismissAll();
    });
  };

  confirmReg() {
    this.authService.reg(this.username, this.password).subscribe(() => {
      this.modalService.dismissAll();
    });
  };

  setButtonText() {
    this.buttonText = this.authService.isLoggedIn() ? 'Add new TODO' : 'Register';
  }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  };
}
