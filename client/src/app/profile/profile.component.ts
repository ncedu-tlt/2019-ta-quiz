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
  private history;
  private URLForHistory;
  private isShowDetails: boolean = false;
  
  constructor(
    private http: HttpClient,
    private token: TokenStorageService,
    private mockHistory: MockUserHistoryService,    
    ) { }

  ngOnInit() {
    this.currentUser = this.token.getUser();
    this.history = this.mockHistory.body;
  }

  getHistory(id){
    this.http.get<any>(this.URLForHistory + id,{})
    .subscribe(obj =>{
        this.history = obj;
    })
  }

  

  showOutputDetails(val, id) {
    if(val.srcElement.nextElementSibling.style['display']=="block") {
      val.srcElement.nextElementSibling.style['display']="none";

    } else {
      this.getHistory(id);
      val.srcElement.nextElementSibling.style['display']="block";
    }



  }
}




