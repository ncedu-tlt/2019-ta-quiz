import {Component, OnInit} from '@angular/core';

import {HttpClientService} from "./http-client.service";
import {Dif} from "./Dif";
import {Theme} from "./Theme";
import {QuestionsId} from "./QuestionsId";

@Component({
  selector: 'app-quiz-setup',
  templateUrl: './quiz-setup.component.html',
  styleUrls: ['./quiz-setup.component.css']
})
export class QuizSetupComponent implements OnInit {
  difs: Dif[] ;
  selectedDifId = '1';
  difUrl = "http://localhost:8080/difficult";

  themes: Theme[] ;
  selectedThemeId = '1';
  themeUrl = "http://localhost:8080/theme";

  questionIdList: QuestionsId[];
  takeQuestionUrl = 'http://localhost:8080/questions/ThemeAndDifId';

  constructor(
    private httpClientService: HttpClientService,
  ) { }

  ngOnInit(): void {
    this.getDifs();
    this.getThemes();
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
    this.httpClientService.getQuestionList(this.selectedThemeId, this.selectedDifId, this.takeQuestionUrl)
      .subscribe(qId => this.questionIdList = qId);
  }

  setSelectedTheme(event) {
    for(var i = 0; i < this.themes.length; i++) {
      if (this.themes[i].themeName == event.target.value) {
        this.selectedThemeId = this.themes[i].id;
      }
    }
  }

  setSelectedDif(event) {
    for(var i = 0; i < this.difs.length; i++) {
      if (this.difs[i].difficultName == event.target.value) {
        this.selectedDifId = this.difs[i].id;
      }
    }
  }
}
