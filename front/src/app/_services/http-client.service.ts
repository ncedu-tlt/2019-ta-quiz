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
    private http: HttpClient
  ) {
  }

  getThemes(url) {
    return this.http.get<Theme[]>(url);
  }

  getDifs(url) {
    return this.http.get<Dif[]>(url);
  }

  getQuestionList(themeId, difId, qty, url) {
    return this.http.get<QuestionsId[]>(url,
      {params: new HttpParams().set('idTheme', themeId).set('idDif', difId).set('qty', qty)}
    );
  }

  getQuestion(id, url){
    return this.http.get<Question>(url  + id);
  }

  postAnswer(){
    // const body = {
    //   params:new HttpParams().set('idUser', userId).set('idAnswer', answerId)
    // }
    debugger;
    return this.http.post('https://quiz-back2020.herokuapp.com/results', {name: 'ibn'}).subscribe();
  }

}
