import { Component, OnInit } from '@angular/core';
import { Todo } from 'src/models/Todo';
import { TodoService } from '../todo.service';

@Component({
  selector: 'todotable',
  templateUrl: './todotable.component.html',
  styleUrls: ['./todotable.component.css']
})
export class TodotableComponent implements OnInit {

  dataSource: Todo[];

  displayedColumns: string[] = ['message', 'category', 'created', 'done', 'doneTime'];

  constructor(private ts: TodoService) {
   }

  ngOnInit(): void {
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
  }
}
