import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { MockStatisticService } from '../_services/mock-statistic.service';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import {LinkToBackService} from '../_services/link-to-back.service';

@Component({
  selector: 'app-game-statistic',
  templateUrl: './game-statistic.component.html',
  styleUrls: ['./game-statistic.component.css']
})
export class GameStatisticComponent implements OnInit {

  statistic;
  specialty;
  private URLForStatistic: string = this.linkToBack.getUrl() + 'results/statistic';

  constructor(
    private http: HttpClient,
    private token: TokenStorageService,
    private mockStatistic: MockStatisticService,
    private linkToBack: LinkToBackService,
  ) { }

  ngOnInit() {
    this.getStatistic();
      }

  getStatistic () {
    this.http.get<any>(this.URLForStatistic,{})
    .subscribe(obj =>{
        this.statistic = obj;
    })
  }

}
