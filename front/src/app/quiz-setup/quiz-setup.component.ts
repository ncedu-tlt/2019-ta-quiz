import {Component, OnInit} from '@angular/core';
import {questionStorageService} from "../_services/questionStorage.Service";
import {Theme} from "../entity/Theme";
import {Dif} from "../entity/Dif";
import {Router} from "@angular/router";
import {LinkToBackService} from '../_services/link-to-back.service';
import {HttpClient, HttpParams} from '@angular/common/http';

@Component({
    selector: 'app-quiz-setup',
    templateUrl: './quiz-setup.component.html',
    styleUrls: ['./quiz-setup.component.css']
})
export class QuizSetupComponent implements OnInit {

    private difficults: Dif[];
    private themes: Theme[];
    private quantityOfQuestions: string = '2';
    private selectedTheme: string = '1';
    private selectedDifficult: string = '1';

    private URLTakeDifficult = this.linkToBack.getUrl() + "difficult";
    private URLTakeTheme = this.linkToBack.getUrl() + "theme";
    private URLTakeQuestion = this.linkToBack.getUrl() + "questions/ThemeAndDifId";

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
        this.http.get<Dif[]>(this.URLTakeDifficult)
            .subscribe(difficults => {
                this.difficults = difficults;
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
        for (let i = 0; i <= this.difficults.length; i++) {
            if (this.difficults[i].difficultName.includes(x)) {
                this.selectedDifficult = this.difficults[i].id;
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

    getQuestion() {
        this.http.get<any>(this.URLTakeQuestion, {
            params: new HttpParams()
                .set('idTheme', this.selectedTheme)
                .set('idDif', this.selectedDifficult)
                .set('qty', this.quantityOfQuestions)
        })
            .subscribe(question => {
                this.storage.setQuestion(question);
            });
    }

    followToQuestionsAndANswers() {
        this.router.navigateByUrl('qanda');
    }

    startAnswering(){
        this.getQuestion();
        this.followToQuestionsAndANswers();
    }
}
