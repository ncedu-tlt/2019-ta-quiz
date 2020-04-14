import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { MockUserHistoryService } from '../_services/mock-user-history.service';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import {LinkToBackService} from '../_services/link-to-back.service';

@Component({
  selector: 'app-games-history',
  templateUrl: './games-history.component.html',
  styleUrls: ['./games-history.component.css']
})
export class GamesHistoryComponent implements OnInit {

  currentUser: any;
  history;
  private URLForGameHistory = this.linkToBack.getUrl() + 'results/';
  private URLForAllHistory = this.linkToBack.getUrl() + 'results/all';
  isShowDetails: boolean = false;

  constructor(
    private http: HttpClient,
    private token: TokenStorageService,
    private mockHistory: MockUserHistoryService,
    private linkToBack: LinkToBackService,
    ) { }

  ngOnInit() {
    this.currentUser = this.token.getUser();
    // this.history = this.mockHistory.body;
    this.getAllHistory();
  }

  getAllHistory () {
    this.http.get<any>(this.URLForAllHistory,{})
    .subscribe(obj =>{
        this.history = obj;
    })
  }

  showOutputDetails(val, id) {
    if(val.srcElement.nextElementSibling.style['display']=="block") {
      val.srcElement.nextElementSibling.style['display']="none";

    } else {
      val.srcElement.nextElementSibling.style['display']="block";
    }
  }

}
