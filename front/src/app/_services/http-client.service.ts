import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Theme} from "../entity/Theme";
import {Dif} from "../entity/Dif";
import {QuestionsId} from "../entity/QuestionsId";
import {Question} from "../entity/Question";


@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient: HttpClient
  ) {
  }

  getThemes(url) {
    return this.httpClient.get<Theme[]>(url);
  }

  getDifs(url) {
    return this.httpClient.get<Dif[]>(url);
  }

  getQuestionList(themeId, difId, qty, url) {
    return this.httpClient.get<QuestionsId[]>(url,
      {params: new HttpParams().set('idTheme', themeId).set('idDif', difId).set('qty', qty)}
    );
  }

  getQuestion(id, url){
    return this.httpClient.get<Question>(url  + id);
  }

  postAnswer(userId, answerId, url){
    // const body = {
    //   params:new HttpParams().set('idUser', userId).set('idAnswer', answerId)
    // }
    const body ='gr';
    debugger;
    return this.httpClient.post(url, body);
  }

}
