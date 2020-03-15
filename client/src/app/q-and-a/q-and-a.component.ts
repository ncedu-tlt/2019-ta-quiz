import {Component, OnInit} from '@angular/core';
import {QuizSetupComponent} from "../quiz-setup/quiz-setup.component";
import {QuestionsId} from "../quiz-setup/QuestionsId";



@Component({
  selector: 'app-q-and-a',
  templateUrl: './q-and-a.component.html',
  styleUrls: ['./q-and-a.component.css']
})
export class QandAComponent implements OnInit {
  questionIdList: QuestionsId[];

  constructor(
    // private quizSetup : QuizSetupComponent,
  ) { }

  ngOnInit(): void {
    this.getQuestionList();
  }


  private getQuestionList() {

  }
}
