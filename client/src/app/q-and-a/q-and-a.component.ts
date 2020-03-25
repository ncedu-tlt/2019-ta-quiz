import {Component, OnInit} from '@angular/core';
import {QuestionsId} from "../entity/QuestionsId";
import {questionStorageService} from "../_services/questionStorage.Service";
import {HttpClientService} from "../_services/http-client.service";
import {Question} from "../entity/Question";
import {TokenStorageService} from "../_services/token-storage.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-q-and-a',
  templateUrl: './q-and-a.component.html',
  styleUrls: ['./q-and-a.component.css']
})
export class QandAComponent implements OnInit {
  private questionIdList: QuestionsId[];
  private qUrl: string = 'http://localhost:8080/questions/';
  private ansUrl: string = 'http://localhost:8080/results';
  private nextQestionId: QuestionsId;
  private question: Question;
  private user;
  private answerId;

  constructor(
    private qStorageService: questionStorageService,
    private httpClient: HttpClientService,
    private tokenStorageService: TokenStorageService,
    private router: Router
  ) {

  }

  ngOnInit(): void {
    this.questionIdList = this.qStorageService.getQuestionIdList();
    this.firstQuestion();
    this.user = this.tokenStorageService.getUser();
  }

  firstQuestion() {
    if (this.questionIdList.length > 0) {
      this.nextQestionId = this.questionIdList.shift();
      this.httpClient.getQuestion(this.nextQestionId, this.qUrl)
        .subscribe(question => {
          this.question = question;
        });
    }
  }


  nextQuestion(event) {
    // for (let i = 0; i <= this.question.answers.length; i++) {
    //   if (this.question.answers.includes(event)) {
    //     this.answerId = this.question.answers[i].id;
    //   }
    // }
    this.httpClient.postAnswer(this.user.id, event, this.ansUrl);

    if (this.questionIdList.length > 0) {
      this.nextQestionId = this.questionIdList.shift();
      this.httpClient.getQuestion(this.nextQestionId, this.qUrl)
        .subscribe(question => {
          this.question = question;
        });
    } else {
      this.router.navigateByUrl('user/result');
    }
  }


}
