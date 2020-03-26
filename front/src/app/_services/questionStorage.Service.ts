import { Injectable } from '@angular/core';
import {Question} from "../entity/Question";

@Injectable({
  providedIn: 'root'
})
export class questionStorageService {

  private question: Question;

  getQuestion(): Question {
    return this.question;
  }

  setQuestion(value: Question) {
    this.question = value;
  }

}
