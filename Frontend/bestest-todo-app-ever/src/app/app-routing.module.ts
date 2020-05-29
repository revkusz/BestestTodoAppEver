import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TodotableComponent } from './todotable/todotable.component';
import { LoginbuttonComponent } from './loginbutton/loginbutton.component';


const routes: Routes = [{
  path: 'todotable',
  component: TodotableComponent
}, {
  path: 'loginbutton',
  component: LoginbuttonComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
