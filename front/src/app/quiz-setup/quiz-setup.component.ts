import {Component, OnInit} from '@angular/core';

import {questionStorageService} from "../_services/questionStorage.Service";
import {Theme} from "../entity/Theme";
import {Dif} from "../entity/Dif";
import {HttpClientService} from "../_services/http-client.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-quiz-setup',
  templateUrl: './quiz-setup.component.html',
  styleUrls: ['./quiz-setup.component.css']
})
export class QuizSetupComponent implements OnInit {

  themes ?: Theme[];
  difs ?: Dif[];

  difUrl = "https://quiz-back2020.herokuapp.com/difficult";
  themeUrl = "https://quiz-back2020.herokuapp.com/theme";
  takeQuestionUrl = 'https://quiz-back2020.herokuapp.com/questions/ThemeAndDifId';

  constructor(
    private httpClient: HttpClientService,
    private qStorageService: questionStorageService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.initThemesDifs();
  }

  initThemesDifs() {
    this.httpClient.getDifs(this.difUrl)
      .subscribe(diffs => {
        this.qStorageService.setDifs(diffs);
        this.difs = diffs;
      });
    this.httpClient.getThemes(this.themeUrl)
      .subscribe(themes => {
        this.qStorageService.setThemes(themes);
        this.themes = themes;
      });
  }


  changeStockDif(event) {
    let x = event.target.value;
    for (let i = 0; i <= this.difs.length; i++) {
      if (this.difs[i].difficultName.includes(x)) {
        this.qStorageService.setSelectedDif(this.difs[i].id);
      }
    }
  }

  changeStockTheme(event) {
    let x = event.target.value;
    for (let i = 0; i <= this.themes.length; i++) {
      if (this.themes[i].themeName.includes(x)) {
        this.qStorageService.setSelectedTheme(this.themes[i].id);
      }
    }
  }

  getQuestionList() {
    this.httpClient.getQuestionList(
      this.qStorageService.getSelectedTheme(),
      this.qStorageService.getSelectedDif(),
      '5',
      this.takeQuestionUrl)
      .subscribe(qId => {
        this.qStorageService.setQuestionIdList(qId);
        this.router.navigateByUrl('user/qanda');
      });
  }
}
