import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import {Diffs} from './Diffs';
import { MessageService } from '../message.service';

@Injectable({
  providedIn: 'root'
})

export class DiffService {

  private diffsUrl = 'api/diffs';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  /** GET heroes from the server */
  getDiffs(): Observable<Diffs[]> {
    return this.http.get<Diffs[]>(this.diffsUrl);
  }



}


