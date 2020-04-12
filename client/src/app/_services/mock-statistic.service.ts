import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MockStatisticService {
   
  body = {
  "rightAswerPersent": 61,
  "totalScore": 8500,
  "totalGames": 26,
  "specialty" :[
      {
      "theme": "История",
      "difficult": "Легкая",
      "rightAswerPersent": 51.5,
      "totalScore": 700,
      "totalGames": 3
      },
      {
      "theme":  "Геграфия",
      "difficult": "Легкая",
      "rightAswerPersent": 68.5,
      "totalScore": 700,
      "totalGames": 3
      },
      {
      "theme":  "История",
      "difficult": "Средняя",
      "rightAswerPersent": 51.5,
      "totalScore": 190,
      "totalGames": 2
      },
      {
      "theme":  "История",
      "difficult": "Средняя",
      "rightAswerPersent": 91.5,
      "totalScore": 1700,
      "totalGames": 3
      }
    ]
  }
  constructor() { }
}
