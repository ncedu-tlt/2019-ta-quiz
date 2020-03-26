import { Injectable } from '@angular/core';
import {QuestionsId} from "../entity/QuestionsId";
import {Dif} from "../entity/Dif";
import {Theme} from "../entity/Theme";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class questionStorageService {

  private _difs: Dif[];
  private _themes: Theme[];
  private  _selectedDif: string = '1';
  private  _selectedTheme: string = '1';
  private _questionId: string;


  getDifs() {
    return this._difs;
  }

  setDifs(value: Dif[]) {
    this._difs = value;
  }

  getThemes(): Theme[] {
    return this._themes;
  }

  setThemes(value: Theme[]) {
    this._themes = value;
  }

  getSelectedDif(): string {
    return this._selectedDif;
  }

  setSelectedDif(value: string) {
    this._selectedDif = value;
  }

  getSelectedTheme(): string {
    return this._selectedTheme;
  }

  setSelectedTheme(value: string) {
    this._selectedTheme = value;
  }

  getQuestionId(): string {
    return this._questionId;
  }

  setQuestionId(value: string) {
    this._questionId = value;
  }

}
