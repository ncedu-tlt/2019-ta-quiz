import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LinkToBackService } from './link-to-back.service';

const API_URL = 'https://quiz-back2020.herokuapp.com/quiz/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
    private linkToBack: LinkToBackService,
    ) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

  getDifs(): Observable<any> {
    return this.http.get(this.linkToBack.getUrl() + 'difficult', {responseType: 'json'})
  }
}
