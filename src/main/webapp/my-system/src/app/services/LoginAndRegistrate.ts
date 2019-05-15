import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from "../data/User";
import {Observable} from "rxjs/internal/Observable";
import {MainWindowComponent} from "../main-window/main-window.component";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class LoginAndRegistrate {
  user: User = null;

  private customersUrl = 'http://localhost:8080/';
  constructor(private httpClient: HttpClient) { }

  loginUser(user: User):  Observable<any>  {
    return this.httpClient.post<User>(this.customersUrl + 'login', user, httpOptions);
  }


  regUser(user: User):  Observable<any> {
    return this.httpClient.post(this.customersUrl + 'registrate', user, httpOptions);
  }

  setData(user: User) {
    this.user = user;
  }

  getData(): User{
    let us = this.user;
    return us;
  }

}
