import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from './auth.service';
import {HeaderComponent} from '../login/header/header.component';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  role: string;
  errorMessage = 'Invalid Credentials';
  successMessage: string;
  invalidLogin = false;
  loginSuccess = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService) {   }

  ngOnInit() {
  }

  handleLogin() {
    this.authenticationService.authenticationService(this.username, this.password).subscribe((result) => {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';

      console.log(sessionStorage);

      const x = sessionStorage.role;
      switch (x) {
        case '[ROLE_Spieler]':
          this.router.navigate(['/play']);
          break;
        case '[ROLE_Kneipenbesitzer]':
          this.router.navigate(['/manage']);
          break;
        case '[ROLE_Admin]':
          this.router.navigate(['/admin-panel']);
          break;
        default:

      }


    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });
  }
  weiterAufReg() {
    this.router.navigate(['/reguser']);
  }

  weiterAufBesitzerReg() {
    this.router.navigate(['/regkneipenbesitzer']);
  }
}
