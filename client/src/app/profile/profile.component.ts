import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../_services/token-storage.service';
import { MockUserHistoryService } from '../_services/mock-user-history.service';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  private history  = this.mockHistory.body;
  private URLForHistory;

  constructor(
    private http: HttpClient,
    private token: TokenStorageService,
    private mockHistory: MockUserHistoryService,
    ) { }

  ngOnInit() {
    this.currentUser = this.token.getUser();
  }

  getHistory(){
    this.http.get<any>(this.URLForHistory,{})
    .subscribe(obj =>{
        this.history = obj;
    })
}



}
