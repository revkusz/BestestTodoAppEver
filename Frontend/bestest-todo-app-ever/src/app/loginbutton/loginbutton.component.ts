import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from '../auth.service';

@Component({
  selector: 'loginbutton',
  templateUrl: './loginbutton.component.html',
  styleUrls: ['./loginbutton.component.css']
})
export class LoginbuttonComponent implements OnInit {
  username: string;
  password: string;

  loginFailed = false;
  loggedIn: boolean;
  buttonText: string;

  constructor(private modalService: NgbModal, private loginService: AuthService) {
    this.loggedIn = !!localStorage.getItem('todoToken');
    this.buttonText = this.loggedIn ? 'Logout' : 'Login';
  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }

  ngOnInit(): void {
  }

  doLoginAction() {
    const token = btoa(`${this.username}:${this.password}`);

    this.loginFailed = false;
    this.username = '';
    this.password = '';

    this.loginService.login(this.username, token).subscribe(data => {
      localStorage.setItem('todoToken', token);
    });
  }

  loginCombined () {
    this.doLoginAction()
    setTimeout(() => {
      if (!localStorage.getItem('todoToken')) {
        this.loginFailed = true;
        this.buttonText = 'Logout';
      } else {
        this.modalService.dismissAll();
      }
    }, 200);
  }

  doLogoutAction() {
    this.loginService.logout();
    this.buttonText = 'Login';
  }

  buttonAction(content) {
    if (localStorage.getItem('todoToken')) {
      this.doLogoutAction();
    } else {
      this.open(content);
    }
  }
}
