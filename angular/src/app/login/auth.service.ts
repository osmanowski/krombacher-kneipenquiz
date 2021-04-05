import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
  USER_ROLE_SESSION_ATTRIBUTE_ROLE = 'role';


  public username: string;
  public password: string;

  constructor(private http: HttpClient) {

  }

  authenticationService(username: string, password: string) {
    return this.http.get(`http://localhost:8080/api/v1/basicauth`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res) => {
      this.username = username;
      this.password = password;
      const userrole = res["role"];
      console.log(userrole);
      this.registerSuccessfulLogin(username, password, userrole);
    }));
  }

  createBasicAuthToken(username: string, password: string) {
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  registerSuccessfulLogin(username, password, role) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    sessionStorage.setItem(this.USER_ROLE_SESSION_ATTRIBUTE_ROLE, role);
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    this.username = null;
    this.password = null;
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) { return false; }
    return true;
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) { return ''; }
    return user;
  }

  getLoggedInUserRole() {
    const role = sessionStorage.getItem(this.USER_ROLE_SESSION_ATTRIBUTE_ROLE)
    if (role === null) { return ''; }
    return role;
  }
}
