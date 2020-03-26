import {Component, OnInit} from '@angular/core';
import {questionStorageService} from "../_services/questionStorage.Service";
import {Question} from "../entity/Question";
import {TokenStorageService} from "../_services/token-storage.service";
import {Router} from "@angular/router";
import {LinkToBackService} from '../_services/link-to-back.service';
import {HttpClient} from '@angular/common/http';
import {Theme} from "../entity/Theme";


@Component({
    selector: 'app-q-and-a',
    templateUrl: './q-and-a.component.html',
    styleUrls: ['./q-and-a.component.css']
})
export class QandAComponent implements OnInit {

    private questionId: string;
    private question: Question;
    private user: any;

    private urlForTakeQuestions: string = this.linkToBack.getUrl() + 'questions';
    private ansUrl: string = this.linkToBack.getUrl() + 'results';


    constructor(
        private storage: questionStorageService,
        private http: HttpClient,
        private tokenStorageService: TokenStorageService,
        private router: Router,
        private linkToBack: LinkToBackService,
    ) {
    }

    ngOnInit(): void {
        this.questionId = this.storage.getQuestionId();
        this.getFirstQuestion();
        this.user = this.tokenStorageService.getUser();
    }

    getFirstQuestion() {
        this.http.get<Question>(this.urlForTakeQuestions + '/' + this.nextQestionId)
            .subscribe(question => {
                this.question = question;
            });

    }

    nextQuestion() {
        // for (let i = 0; i <= this.question.answers.length; i++) {
        //   if (this.question.answers.includes(event)) {
        //     this.answerId = this.question.answers[i].id;
        //   }
        // }
        // debugger;
        this.http.post<Theme>(this.ansUrl, {id: 1, themeName: 'История'}).subscribe();

        // if (this.lenght > 1) {
        //     this.nextQestionId = this.questionIdList.shift();
        //     this.http.getQuestion(this.nextQestionId, this.urlForTakeQuestions)
        //         .subscribe(question => {
        //             this.question = question;
        //         });
        //     this.lenght = this.questionIdList.length;
        // } else {
        //     debugger;
        //     this.router.navigateByUrl('result');
        // }
    }


}
