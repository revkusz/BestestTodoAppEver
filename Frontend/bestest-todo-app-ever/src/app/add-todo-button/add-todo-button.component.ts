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

  categories: Category[];

  constructor(private modalService: NgbModal, private categoryService: CategoryService, 
              private authService: AuthService, private todoService: TodoService,
              private eventEmitter: EventEmitterService) { }

  ngOnInit(): void {
  };
  
  open(content) {
    if (!this.authService.isLoggedIn()) {
      return;
    }

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

      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
    });
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
}
