import {Component, OnInit, EventEmitter} from '@angular/core';
import {questionStorageService} from "../_services/questionStorage.Service";
import {Question} from "../entity/Question";
import {TokenStorageService} from "../_services/token-storage.service";
import {Router} from "@angular/router";
import {LinkToBackService} from '../_services/link-to-back.service';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import {Answer} from "../entity/Answer";


@Component({
    selector: 'app-q-and-a',
    templateUrl: './q-and-a.component.html',
    styleUrls: ['./q-and-a.component.css']
})
export class QandAComponent implements OnInit {

    question: Question;
    private user;
    private answerId = '-1';
    private URLForAnswers: string = this.linkToBack.getUrl() + 'results';
    private questionId;
    private idList = [];

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
        if (this.answerId != "-1"){
        this.http.post<Question>(this.URLForAnswers, {}, {
            params: new HttpParams()
                .set('idAnswer', this.answerId)
            })
            .subscribe(
                question => {
                    if(question.id != '-1'){
                        this.question = question;
                        this.answerId = '-1';
                    } else {
                        this.router.navigateByUrl('result');
                    }
                }
            )
        } else {
            let isSendRandom = confirm("Выберите вариант ответа - Отмена\nИли доверьтесь воле случая - OK");
            if (isSendRandom){
              for (let answer of this.question.answers){
                  this.idList.push(answer.id);
               }
               this.answerId = this.idList[Math.floor(Math.random() * Math.floor(this.question.answers.length))];
               this.idList = [];
               this.nextQuestion();
            }
        }
    }

    followToResults(): void {
        this.router.navigateByUrl('result');
    }

}
