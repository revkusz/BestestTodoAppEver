import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Todo } from 'src/models/Todo';
import { TodoService } from '../todo.service';
import { EventEmitterService } from '../event-emitter.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'todotable',
  templateUrl: './todotable.component.html',
  styleUrls: ['./todotable.component.css']
})
export class TodotableComponent implements OnInit {

  dataSource: Todo[];

  displayedColumns: string[] = ['message', 'category', 'created', 'done', 'doneTime'];

  @ViewChild('content') content: ElementRef;

  message: string;
  category: string;
  created: Date;
  done: boolean
  doneTime: Date;
  todoId: string;
  catId: string;

  origMessage: string;
  origDone: boolean;
  origCatId: string;

  constructor(private ts: TodoService, private eventEmitterService: EventEmitterService, private modalService: NgbModal) {
  }

  ngOnInit(): void {
    const token = localStorage.getItem('todoToken');

    if (token) {
      this.getData();
    }

    if (this.eventEmitterService.getSubsVar === undefined) {
      this.eventEmitterService.getSubsVar = this.eventEmitterService.
      invoketGetTodo.subscribe(() => {
        this.getData();
      });
    }

    if (this.eventEmitterService.clearSubsVar === undefined) {
      this.eventEmitterService.clearSubsVar = this.eventEmitterService.
      invokeClearTodo.subscribe(() => {
        this.clearData();
      })
    }
  };

  getData() {
    const token = localStorage.getItem('todoToken');
    const username = localStorage.getItem('todoUsername');
    this.ts.getUserTodos(username, token).subscribe(data => {
      this.dataSource = data.map(todo => {
        let temp: Todo;
        temp = {
          message: todo.message,
          created: todo.created,
          done: todo.done,
          catname: todo.category.name,
          catcolor: todo.category.color,
          doneTime: todo.doneTime,
          catId: todo.category.id,
          todoId: todo.id
        }

        return temp;
      })
    },
    err => console.log(err));
  };

  clearData() {
    this.dataSource = [];
  };

  onRowClick(row: Todo) {
    this.message = row.message;
    this.doneTime = row.doneTime;
    this.done = row.done;
    this.category = row.catId;
    this.created = row.created;
    this.todoId = row.todoId;
    this.catId = row.catId;

    this.origMessage = row.message;
    this.origDone = row.done;
    this.origCatId = row.catId;

    this.open(this.content);
  };
    
  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  };

  clearAndDismiss() {
    this.message = '';
    this.doneTime = null;
    this.done = undefined;
    this.category = '';
    this.created = null;

    this.modalService.dismissAll();
  };

  sendEdit() {
    const token = localStorage.getItem('todoToken');
    const username = localStorage.getItem('todoUsername');

    this.ts.editTodo(username, token, this.todoId, this.message, this.done, this.catId).subscribe(data => {
      this.eventEmitterService.onInvokeGetTodo();
    });

    this.modalService.dismissAll();
  };
}
