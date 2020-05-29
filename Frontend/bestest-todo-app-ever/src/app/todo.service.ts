import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EMPTY } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  url = 'http://localhost:8080/todo';

  headerData = {
    username: 'sari',
    password: 'asdvagyol12',
    Authorization: 'Basic c2FyaTphc2R2YWd5b2wxMg=='
  }

  constructor(private http: HttpClient) { }

  getUserTodos () {
    return this.http.get<any[]>(this.url + '/all/sari', {
      headers: new HttpHeaders(this.headerData)
    });
  }
}
