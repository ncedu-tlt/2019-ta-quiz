import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LinkToBackService {
  private url = 'http://localhost:8080/';
  constructor() { }

  getUrl() {
    return this.url;
  }

}
