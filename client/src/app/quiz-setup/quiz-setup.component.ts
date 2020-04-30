import {Component, OnInit} from '@angular/core';
import {questionStorageService} from "../_services/questionStorage.Service";
import {Theme} from "../entity/Theme";
import {Difficult} from "../entity/Difficult";
import {Router} from "@angular/router";
import {LinkToBackService} from '../_services/link-to-back.service';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Question} from "../entity/Question";

@Component({
    selector: 'app-quiz-setup',
    templateUrl: './quiz-setup.component.html',
    styleUrls: ['./quiz-setup.component.css']
})
export class QuizSetupComponent implements OnInit {

    difficulties: Difficult[];
    themes: Theme[];
    private quantityOfQuestions: string = '2';
    private selectedTheme: string = '1';
    private selectedDifficult: string = '1';

    private URLTakeDifficult = this.linkToBack.getUrl() + "difficult";
    private URLTakeTheme = this.linkToBack.getUrl() + "theme";
    private URLTakeQuestion = this.linkToBack.getUrl() + "questions/ThemeAndDifId";
    private question: Question;

    constructor(
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
            if (this.difficulties[i].difficultName.includes(x)) {
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

    startAnswering() {
        this.http.get<Question>(this.URLTakeQuestion, {
            params: new HttpParams()
                .set('idTheme', this.selectedTheme)
                .set('idDif', this.selectedDifficult)
                .set('qty', this.quantityOfQuestions)
        })
            .subscribe(question => {
                this.question = question;
                this.storage.setQuestion(this.question);
                this.router.navigateByUrl('qanda');
            });
    }



}
