import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import {LinkToBackService} from '../_services/link-to-back.service';
import { MockResultService } from '../_services/mock-result.service';
import {Router} from "@angular/router";

@Component({
    selector: 'app-result',
    templateUrl: './result.component.html',
    styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

    private URLToResult = this.linkToBack.getUrl() + 'results';
    results;
    private fullQuestion;
    private result:string = "assets/images/graz";

    constructor(
        private http: HttpClient,
        private linkToBack: LinkToBackService,
        private mockResult: MockResultService,
        private router: Router,
    ) {
    }

    ngOnInit() {
        this.getResult();
        // this.results = this.mockResult.body;
        this.fullQuestion = this.results.questions
    }

    getResult(){
        this.http.get<any>(this.URLToResult,{})
        .subscribe(obj =>{
            this.results = obj;
        })
    }

    goTroughAgain() {
        this.router.navigateByUrl('quiz-setup');
    }
}
