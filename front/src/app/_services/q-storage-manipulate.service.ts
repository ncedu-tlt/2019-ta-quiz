import { Injectable } from '@angular/core';
import {HttpClientService} from "./http-client.service";
import {questionStorageService} from "./questionStorage.Service";

@Injectable({
  providedIn: 'root'
})
export class QStorageManipulateService {



  constructor(
    private httpClientService: HttpClientService,
  ) { }

  getDifsFromServer(): void {

  }

  getThemesFromServer(): void {

  }
















}
