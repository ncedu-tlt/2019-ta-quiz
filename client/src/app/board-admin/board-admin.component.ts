import { Component, OnInit } from '@angular/core';
import {Difficult} from "../entity/Difficult";
import {Theme} from "../entity/Theme";
import {HttpClient} from "@angular/common/http";

import {LinkToBackService} from "../_services/link-to-back.service";


@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  private URLTakeDifficult = this.linkToBack.getUrl() + "difficult";
  private URLTakeTheme = this.linkToBack.getUrl() + "theme";
  private URLForQuestion: string = this.linkToBack.getUrl() + 'questions/add';

  private difficulties: Difficult[];
  private themes: Theme[];
  private selectedTheme: string = '1';
  private selectedDifficult: string = '1';

  private questionName: string;
  private correctAnswer: string;
  private wrongAnswers = [];
  private wrongAnswer: string;
  private allAnswers = [];
  private responseErroreMessage;

  constructor(
    private http: HttpClient,
    private linkToBack: LinkToBackService,
  ) {
  }

  ngOnInit(): void {
    this.initDifficults();
    this.initThemes();
    this.questionName = '';
  }

  initDifficults() {
    this.http.get<Difficult[]>(this.URLTakeDifficult)
      .subscribe(difficulties => {
        this.difficulties = difficulties;
      });
  }

  initThemes() {
    this.http.get<Theme[]>(this.URLTakeTheme)
      .subscribe(themes => {
        this.themes = themes;
      });
  }

  changeStockDifficult(event) {
    let x = event.target.value;
    for (let i = 0; i <= this.difficulties.length; i++) {
      if (x.includes(this.difficulties[i].difficultName)) {
        this.selectedDifficult = this.difficulties[i].id;
      }
    }
  }

  changeStockTheme(event) {
    let x = event.target.value;
    for (let i = 0; i <= this.themes.length; i++) {
      if (this.themes[i].themeName.includes(x)) {
        this.selectedTheme = this.themes[i].id;
      }
    }
  }

  addToWrongAnswersArray() {
    this.wrongAnswers.push({answerText: this.wrongAnswer, answerIsCorrect: false});
    this.allAnswers.push({answerText: this.wrongAnswer, answerIsCorrect: false});
    this.wrongAnswer = '';
  }

  addQuestion() {
    let isCorrectAnswerEmpty = false;
    let isQuestionTextEmpty = false;
    let isGoodRequest = false;
    let isBadRequest = false;
    let mistakes = 0;
    
    if (!this.correctAnswer){
      isCorrectAnswerEmpty = true;
      return;
    } else {
      isCorrectAnswerEmpty = false;
      this.allAnswers.push({answerText: this.correctAnswer, answerIsCorrect: true});
    }

    if(!this.questionName){
      isQuestionTextEmpty = true;
      return; 
    } else {
      isQuestionTextEmpty = false;
    }

    for (let wrongAswer of this.wrongAnswers){
      
      if (this.correctAnswer == wrongAswer.answerText){  
        mistakes += 1;
      }
      }
      if (mistakes > 0){
        if ((mistakes%10) == 1){
          let message = "правильный ответ совпадает с " + mistakes + " неправильным!";
          alert(message);
        }  else {
          let message = "правильный ответ совпадает с " + mistakes + " неправильными!";
          alert(message);
        }
        return;
      
    }
    
    let body = {
      questionName: this.questionName,
      themeId: this.selectedTheme,
      difficultId: this.selectedDifficult,
      answers: this.allAnswers
    }
    
    this.http.post<any>(this.URLForQuestion, body).subscribe(
      data => {
        alert("вопрос добавлен в базу");
        this.clearForms();
      },    
      err => {
        alert("При добавлении вопроса произошла ошибка: " + err.error.message); 
      }
    );
  }

  deleteFromWrongAnswers(i) {
    this.wrongAnswers.splice(i, 1);
  }

  clearForms(){
    this.questionName = '';
    this.correctAnswer = '';
    this.wrongAnswers = [];
  }
}

