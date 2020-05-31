import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Todo } from 'src/models/Todo';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  url = 'http://localhost:8080/todo';

  constructor(private http: HttpClient) { }

  getUserTodos (username: string, token:string) {
    return this.http.get<any[]>(this.url + `/all/${username}`, {
      headers: new HttpHeaders({
        Authorization: `Basic ${token}`
      })
    });
  };

  postTodo (username: string, token:string, todoMessage: string, catId: string) {
    return this.http.post(this.url, {
      message: todoMessage,
      done: false,
      categor_id: catId,
      owner_id: username  
    }, {
      headers: new HttpHeaders({
        Authorization: `Basic ${token}`
      })
    })
  };

  editTodo (username: string, token: string, todoId: string, newMessage: string, newDone: boolean, catId) {
    return this.http.put(this.url + `/${todoId}`, {
      owner_id: username,
      categor_id: catId,
      done: newDone,
      message: newMessage
    }, {
      headers: new HttpHeaders({
        Authorization: `Basic ${token}`
      })
    });
  };
}
