import { Injectable, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {

  invoketGetTodo = new EventEmitter();
  invokeClearTodo = new EventEmitter();
  getSubsVar: Subscription;
  clearSubsVar: Subscription;

  constructor() { }

  onInvokeGetTodo() {
    this.invoketGetTodo.emit('');
  }

  onInvokeClearTodo() {
    this.invokeClearTodo.emit('');
  }
}
