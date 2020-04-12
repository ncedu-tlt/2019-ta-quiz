import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MockUserHistoryService {

  body={
    "games": [
		{
			"idGame": "f87681f2-0224-4dba-a58b-7fdb4b1ef337" ,
			"date": "2020-04-02 14:36:43" ,
			"theme": "Легкий" ,
			"difficult": "История" ,
			"score": 200
		},
		{
			"idGame": "b9d65335-9277-4038-892d-477cb94c755f" ,
			"date": "2020-04-06 20:29:25" ,
			"theme": "Легкий" ,
			"difficult": "История" ,
			"score": 300
		}
		]
}

  constructor() { }
}
