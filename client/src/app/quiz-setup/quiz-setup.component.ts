import {Component, OnInit, OnDestroy} from '@angular/core';

import {HttpClientService} from "./http-client.service";
import {Dif} from "./Dif";
import {Theme} from "./Theme";
import {QuestionsId} from "./QuestionsId";
import {questionStorageService} from "../_services/questionStorage.Service";
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-quiz-setup',
  templateUrl: './quiz-setup.component.html',
  styleUrls: ['./quiz-setup.component.css']
})
export class QuizSetupComponent implements OnInit, OnDestroy {
  difs: Dif[];
  selectedDif;
  serviceSelectedDif;   //for debugging
  difUrl = "http://localhost:8080/difficult";

  themes: Theme[];
  selectedTheme;
  serviceSelctedTheme;    //for debugging
  themeUrl = "http://localhost:8080/theme";

  questionIdList: QuestionsId[];
  takeQuestionUrl = 'http://localhost:8080/questions/ThemeAndDifId';

  constructor(
    private httpClientService: HttpClientService,
    private questionStorageService: questionStorageService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.getDifs();
    this.getThemes();
  }

  ngOnDestroy(): void {
    this.questionStorageService.setQuestionList(this.questionIdList);
  }

  getDifs(): void {
    this.httpClientService.getDifs(this.difUrl)
      .subscribe(diffs => this.difs = diffs);
  }

  getThemes(): void {
    this.httpClientService.getThemes(this.themeUrl)
      .subscribe(themes => this.themes = themes);
  }

  getQuestionList() {
    this.httpClientService.getQuestionList(
      this.questionStorageService.getSelectedTheme(),
      this.questionStorageService.getSelctedDif(),
      '5',
      this.takeQuestionUrl)
      .subscribe(qId => this.questionStorageService.setQuestionList(qId));
    this.questionIdList = this.questionStorageService.getQuestionList();

  }

  setSelectedTheme(event) {
    let x = event.target.value;
    console.log(this.questionStorageService.getQuestionList());
    //console.log(x);
   for (let i = 0; i <= this.themes.length; i++){
     console.log(this.themes[i].themeName);
      if (this.themes[i].themeName.includes(x)){
        console.log("change theme");
        this.questionStorageService.setTheme(this.themes[i].id);
        break;
      }

    }
  }

  setSelectedDif(event) {
    let x = event.target.value;
    for (let i = 0; i <= this.difs.length; i++) {
      if (this.difs[i].difficultName.includes(x)) {
        this.questionStorageService.setDif(this.difs[i].id);
        break;
      }
    }
  }




}
