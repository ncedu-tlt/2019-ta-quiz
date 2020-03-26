import {Component, OnInit, EventEmitter} from '@angular/core';
import {questionStorageService} from "../_services/questionStorage.Service";
import {Question} from "../entity/Question";
import {TokenStorageService} from "../_services/token-storage.service";
import {Router} from "@angular/router";
import {LinkToBackService} from '../_services/link-to-back.service';
import {HttpClient, HttpParams} from '@angular/common/http';


@Component({
    selector: 'app-q-and-a',
    templateUrl: './q-and-a.component.html',
    styleUrls: ['./q-and-a.component.css']
})
export class QandAComponent implements OnInit {

    private question: Question;
    private user;
    private answerId;
    private URLForAnswers: string = this.linkToBack.getUrl() + 'results';
    private questionId;
    private answerText;
    constructor(
        private storage: questionStorageService,
        private http: HttpClient,
        private tokenStorageService: TokenStorageService,
        private router: Router,
        private linkToBack: LinkToBackService,
    ) {
    }

    ngOnInit(): void {
        this.question = this.storage.getQuestion();
        this.user = this.tokenStorageService.getUser();
    }

    checked(ev) {
        this.answerText = ev.originalTarget.firstChild.nodeValue;
        for (let i = 0; i < this.question.answers.length; i++) {
            console.log(this.question.answers[0].answerText);
            if (this.question.answers[i].answerText.includes(answertext)) {
                this.answerId = this.question.answers[i].id;
            }
        }
    }

    

    followToResults(): void {
        this.router.navigateByUrl('result');
    }

}
