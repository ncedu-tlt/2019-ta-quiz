import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import {LinkToBackService} from '../_services/link-to-back.service';

@Component({
    selector: 'app-result',
    templateUrl: './result.component.html',
    styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

    private URLToResult = this.linkToBack.getUrl() + 'results';
    private results;

    constructor(
        private http: HttpClient,
        private linkToBack: LinkToBackService,
    ) {
    }

    ngOnInit() {
        this.http.get<any>(this.URLToResult,{})
        .subscribe(obj =>{
            this.results = obj;
        })
    }

}
