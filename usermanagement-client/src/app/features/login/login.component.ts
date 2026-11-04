
import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email: string = '';
  password: string = '';
  rememberMe: boolean = false;
  showPassword: boolean = false;

  login() {

    if (!this.email || !this.password) {
      alert('Please enter email and password');
      return;
    }

    console.log('Login Details:', {
      email: this.email,
      password: this.password,
      rememberMe: this.rememberMe
    });

    alert('Login UI working successfully!');
  }
}
