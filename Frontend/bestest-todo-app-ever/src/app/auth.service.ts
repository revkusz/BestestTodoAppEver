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
      'Content-Type': 'application/json',
      Authorization: `Basic ${token}`,
      observe: 'response',
      response: 'json'
      })
    });
  };

  reg(username: string, password: string) {
    const hack = 'Basic R09EOk1JTkVLPw==';
    //We need this bcs backend is a lazy piece of wonderful person

    return this.http.post(`${this.url}user/create`, {
      username,
      password
    }, {
      headers: new HttpHeaders({
        Authorization: hack
      })
    });
  };

  logout() {
    window.localStorage.removeItem('todoToken');
  };

  isLoggedIn() {
    return !!window.localStorage.getItem('todoToken');
  };

  getUserName() {
    return window.localStorage.getItem('todoUsername');
  };

  getToken() {
    return window.localStorage.getItem('todoToken');
  };
}
