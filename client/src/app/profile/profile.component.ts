import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../_services/token-storage.service';
import { MockUserHistoryService } from '../_services/mock-user-history.service';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import { MockResultService } from '../_services/mock-result.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  private history;
  private URLForHistory;
  private isShowDetails: boolean = false;
  private URLToResult;
  private results;
  private fullQuestion;

  constructor(
    private http: HttpClient,
    private token: TokenStorageService,
    private mockHistory: MockUserHistoryService,
    private mockResult: MockResultService,
    ) { }

  ngOnInit() {
    this.currentUser = this.token.getUser();
    this.history = this.mockHistory.body;
    this.results = this.mockResult.body;
    this.fullQuestion = this.results.questions;
  }

  getHistory(){
    this.http.get<any>(this.URLForHistory,{})
    .subscribe(obj =>{
        this.history = obj;
    })
  }

  getResult(){
    this.http.get<any>(this.URLToResult,{})
    .subscribe(obj =>{
        this.results = obj;
    })
  }

  showOutputDetails(val) {
    if(val.srcElement.nextElementSibling.style['display']=="block") {
      val.srcElement.nextElementSibling.style['display']="none";

    } else {
      val.srcElement.nextElementSibling.style['display']="block";
    }



  }
}




