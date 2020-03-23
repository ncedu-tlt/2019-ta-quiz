import {Component, OnInit} from '@angular/core';
import {QuestionsId} from "../quiz-setup/QuestionsId";
import {questionStorageService} from "../_services/questionStorage.Service";
import {HttpClientService} from "../quiz-setup/http-client.service";


@Component({
  selector: 'app-q-and-a',
  templateUrl: './q-and-a.component.html',
  styleUrls: ['./q-and-a.component.css']
})
export class QandAComponent implements OnInit {
  questionIdList: QuestionsId[];
  selectedTheme;
  constructor(private questionStorageService: questionStorageService) {
  }

  ngOnInit(): void {
    this.questionIdList = this.questionStorageService.getQuestionList();
    this.selectedTheme = this.questionStorageService.getSelectedTheme();
    console.log(this.selectedTheme);
    console.log(this.questionStorageService.getSelectedTheme());
    debugger;
  }


  nextQuestion() {
    if (this.questionIdList.length > 0) {
      this.questionStorageService.getQuestionList().splice(0, 1);
      this.questionIdList.splice(0,1);

    } else {
      //this.questionStorageService.clearData();
    }
    console.log(this.questionStorageService.getQuestionList());
  }


}
