import {Component, OnInit} from '@angular/core';
import {QuestionsId} from "../entity/QuestionsId";
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

    private questionIdList: QuestionsId[];
    private urlForTakeQuestions: string = this.linkToBack.getUrl() + 'questions';
    private ansUrl: string = this.linkToBack.getUrl() + 'results';
    private nextQestionId: QuestionsId;
    private question: Question;
    private user;
    private answerId;
    private lenght;

    constructor(
        private qStorageService: questionStorageService,
        private httpClient: HttpClient,
        private tokenStorageService: TokenStorageService,
        private router: Router,
        private linkToBack: LinkToBackService,
    ) {

    }

    ngOnInit(): void {
        this.questionIdList = this.qStorageService.getQuestionIdList();
        this.lenght = this.questionIdList.length;
        this.firstQuestion();
        this.user = this.tokenStorageService.getUser();
    }

    firstQuestion() {
        if (this.questionIdList.length > 0) {
            this.nextQestionId = this.questionIdList.shift();
            this.httpClient.get<Question>( this.urlForTakeQuestions + '/' + this.nextQestionId)
                .subscribe(question => {
                    this.question = question;
                });
        }
    }


    nextQuestion() {
        // for (let i = 0; i <= this.question.answers.length; i++) {
        //   if (this.question.answers.includes(event)) {
        //     this.answerId = this.question.answers[i].id;
        //   }
        // }
        // debugger;
        this.httpClient.post<Theme>(this.ansUrl, {id: 1, themeName: 'История'}).subscribe();

        // if (this.lenght > 1) {
        //     this.nextQestionId = this.questionIdList.shift();
        //     this.httpClient.getQuestion(this.nextQestionId, this.urlForTakeQuestions)
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
