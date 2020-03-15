import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Theme} from "./Theme";
import {Dif} from "./Dif";
import {QuestionsId} from "./QuestionsId";


@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient:HttpClient
  ) {
  }

  getThemes(url)
  {
    return this.httpClient.get<Theme[]>(url);
  }

  getDifs(url)
  {
    return this.httpClient.get<Dif[]>(url);
  }

  getQuestionList(themeId, difId, url) {
    return this.httpClient.get<QuestionsId[]>(url,
      {params: new HttpParams().set('idTheme', themeId).set('idDif', difId)}
    );
  }
}
