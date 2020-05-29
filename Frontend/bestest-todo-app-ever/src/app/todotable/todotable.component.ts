import { Component, OnInit } from '@angular/core';
import { Todo } from 'src/models/Todo';
import { TodoService } from '../todo.service';
import { EventEmitterService } from '../event-emitter.service';

@Component({
  selector: 'todotable',
  templateUrl: './todotable.component.html',
  styleUrls: ['./todotable.component.css']
})
export class TodotableComponent implements OnInit {

  dataSource: Todo[];

  displayedColumns: string[] = ['message', 'category', 'created', 'done', 'doneTime'];

  constructor(private ts: TodoService, private eventEmitterService: EventEmitterService) {
   }

  ngOnInit(): void {
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
    this.ts.getUserTodos().subscribe(data => {
      this.dataSource = data.map(todo => {
        let asd: Todo;
        asd = {
          message: todo.message,
          created: todo.created,
          done: todo.done,
          catname: todo.category.name,
          catcolor: todo.category.color,
          doneTime: todo.doneTime
        }

        return asd;
      })
    },
    err => console.log(err));
  };

  clearData() {
    this.dataSource = [];
  };
}
