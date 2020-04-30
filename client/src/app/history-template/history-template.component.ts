import { Component, OnInit , Input} from '@angular/core';
import { MockResultService } from '../_services/mock-result.service';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import {LinkToBackService} from '../_services/link-to-back.service';

@Component({
  selector: 'app-history-template',
  templateUrl: './history-template.component.html',
  styleUrls: ['./history-template.component.css']
})
export class HistoryTemplateComponent implements OnInit {

  @Input() private idGame: string;
  private URLToResult = this.linkToBack.getUrl() + 'results/' + this.idGame;
  results;
  private fullQuestion;


    constructor(
        private http: HttpClient,
        private linkToBack: LinkToBackService,
        private mockResult: MockResultService,
    ) {
    }

    ngOnInit() {
        this.getResult();
        // this.results = this.mockResult.body;
        this.fullQuestion = this.results.questions
    }

    getResult(){
        this.http.get<any>(this.linkToBack.getUrl() + 'results/' + this.idGame,{})
        .subscribe(obj =>{
            this.results = obj;
        })
    }

}
