import {Component, OnInit, EventEmitter} from '@angular/core';
import {questionStorageService} from "../_services/questionStorage.Service";
import {Question} from "../entity/Question";
import {TokenStorageService} from "../_services/token-storage.service";
import {Router} from "@angular/router";
import {LinkToBackService} from '../_services/link-to-back.service';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';


@Component({
    selector: 'app-q-and-a',
    templateUrl: './q-and-a.component.html',
    styleUrls: ['./q-and-a.component.css']
})
export class QandAComponent implements OnInit {

    private question: Question;
    private user;
    private answerId = '-1';
    private URLForAnswers: string = this.linkToBack.getUrl() + 'results';
    private questionId;

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

    checked(value) {
        for (let i = 0; i < this.question.answers.length; i++) {
            if (value.target.textContent.includes(this.question.answers[i].answerText)) {
                this.answerId = this.question.answers[i].id;
                console.log(this.answerId);
            }
        }
    }

    nextQuestion() {
        this.http.post<Question>(this.URLForAnswers, {}, {
            params: new HttpParams()
                .set('idAnswer', this.answerId)
            })
            .subscribe(
                question => {
                    if(question.id != '-1'){
                        this.question = question;
                    } else {
                        this.router.navigateByUrl('result');
                    }
                }
            )
    }

    followToResults(): void {
        this.router.navigateByUrl('result');
    }

}
