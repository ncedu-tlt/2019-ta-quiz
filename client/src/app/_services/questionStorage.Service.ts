import { Injectable } from '@angular/core';
import {Question} from "../entity/Question";

@Injectable({
  providedIn: 'root'
})
export class questionStorageService {

  private question: Question;

  setQuestion(value: Question): void {
    this.question = value;
  }

  getQuestion(): Question {
    return this.question;
  }
}
