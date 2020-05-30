import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from 'src/models/Category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  url = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getCategories(username: string, token: string) {
    return this.http.get<Category[]>(`${this.url}category/all/${username}`, {
      headers: { 
        'Content-Type': 'application/json',
        Authorization: `Basic ${token}`,
        observe: 'response',
        response: 'json'
      }
    })
  }
}
