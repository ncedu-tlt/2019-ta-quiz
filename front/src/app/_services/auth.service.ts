import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LinkToBackService } from './link-to-back.service';



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  AUTH_API = this.linkToBack.getUrl() + 'auth/';
  constructor(
    private http: HttpClient,
    private linkToBack: LinkToBackService,
    ) { }

  login(credentials): Observable<any> {
    return this.http.post(this.AUTH_API + 'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post(this.AUTH_API + 'signup', {
      username: user.username,
      password: user.password
    }, httpOptions);
  }

  loggedIn() {
    return !!sessionStorage.getItem('auth-token')
  }
}
