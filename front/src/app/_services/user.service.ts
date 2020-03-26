import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LinkToBackService } from './link-to-back.service';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  API_URL = this.linkToBack.getUrl();
  constructor(
    private http: HttpClient,
    private linkToBack: LinkToBackService,
    ) { }

  
  getUserBoard(): Observable<any> {
    return this.http.get(this.API_URL + 'quiz-setup', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(this.API_URL + 'admin', { responseType: 'text' });
  }

  
}
