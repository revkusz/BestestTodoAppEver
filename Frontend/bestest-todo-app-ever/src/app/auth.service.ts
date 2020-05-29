import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Todo } from 'src/models/Todo';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url = 'http://localhost:8080/';

  headerData = {
    'Content-Type': 'application/json',
    'Authorization': ''
  };

  constructor(private http: HttpClient) { };

  login(username: string, token: string) {

    return this.http.get<Todo[]>(`${this.url}todo/all/${username}`, {headers: new HttpHeaders({
      Authorization: `Basic ${token}`,
      observe: 'response',
      response: 'json'
      })
    });
  };

  logout() {
    window.localStorage.removeItem('todoToken');
  };

  isLoggedIn() {
    return !!window.localStorage.getItem('todoToken');
  }
}
