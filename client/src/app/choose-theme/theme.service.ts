import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { MessageService } from '../message.service';
import {Themes} from './Themes';

@Injectable({
  providedIn: 'root'
})

export class ThemeService {

  private themesUrl = 'api/themes';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  /** GET heroes from the server */
  getThemes(): Observable<Themes[]> {
    return this.http.get<Themes[]>(this.themesUrl);
  }

}
