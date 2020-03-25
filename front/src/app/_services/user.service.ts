import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LinkToBackService } from './link-to-back.service';

const API_URL = 'https://quiz-back2020.herokuapp.com/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
    private linkToBack: LinkToBackService,
    ) { }

  
  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'quiz-setup', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

  
}
