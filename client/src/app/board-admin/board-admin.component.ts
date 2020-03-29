import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import {Difficult} from "../entity/Difficult";
import {Theme} from "../entity/Theme";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";
import {questionStorageService} from "../_services/questionStorage.Service";
import {LinkToBackService} from "../_services/link-to-back.service";
import {Question} from "../entity/Question";
import {Answer} from "../entity/Answer";

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  private question: Question;
  private difficulties: Difficult[];
  private themes: Theme[];
  private selectedTheme: string = '1';
  private selectedDifficult: string = '1';

  private URLTakeDifficult = this.linkToBack.getUrl() + "difficult";
  private URLTakeTheme = this.linkToBack.getUrl() + "theme";
  private URLForQuestion: string = this.linkToBack.getUrl() + 'questions/add';

  constructor(
    private userService: UserService,
    private http: HttpClient,
    private router: Router,
    private storage: questionStorageService,
    private linkToBack: LinkToBackService,
  ) {
  }

  ngOnInit(): void {
        this.initDifficults();
        this.initThemes();
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

  addQuestion(question: Question, theme: Theme, difficult: Difficult) {
    const body = {
      questionName: question.questionName,
      themeId: theme.id,
      difficultId: difficult.id,
      answers: [
        {
          answerText: question.answers,
          answerIsCorrect: question.answers,
        },
        {
          answerText: question.answers,
          answerIsCorrect: question.answers,
        },
        {
          answerText: question.answers,
          answerIsCorrect: question.answers,
        },
        {
          answerText: question.answers,
          answerIsCorrect: question.answers,
        }
      ]
    }
    return this.http.post(this.URLForQuestion, body)
  }
}

