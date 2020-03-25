import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LinkToBackService {
  private url = 'https://quiz-back2020.herokuapp.com/';
  constructor() { }

  getUrl() {
    return this.url;
  }

}
