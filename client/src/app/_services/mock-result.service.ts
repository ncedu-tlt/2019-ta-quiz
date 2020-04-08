import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MockResultService {
  
  body = {
    
      "questions": [
          {

          "questionName": "Год основания Рима",
              "answers": [

              {
              "answerText": "357",
              "answerIsCorrect": false,
              "userAnswer": false
              },

              {
              "answerText": "800",
              "answerIsCorrect": false,
              "userAnswer": true                  
              },
              {
                "answerText": "573",
                "answerIsCorrect": false,
                "userAnswer": false                    
              },

              {
                "answerText": "753",
                "answerIsCorrect": true,
                "userAnswer": false                    
              }
              ]
          },

          
      {
        "questionName": "Какой город был столицей Древнего Египта?",
              "answers": [
          {
            "answerText": "Луксор",
            "answerIsCorrect": false,
            "userAnswer": false
          },
          {
            "answerText": "Мемфис",
            "answerIsCorrect": true,
            "userAnswer": true                  
          },
          {
                      "answerText": "Гиза",
                      "answerIsCorrect": false,
            "userAnswer": false                    
          },
          {
            "answerText": "Каир",
            "answerIsCorrect": false,
            "userAnswer": false                    
          }
        ]
          },
          {
        "questionName": "Когда Древний Египет стал единой страной?",
              "answers": [
          {
            "answerText": "~3000 г. до н. э.",
            "answerIsCorrect": true,
            "userAnswer": false
          },
          {
            "answerText": "~2000 г. до н. э.",
            "answerIsCorrect": false,
            "userAnswer": false                  
          },
          {
                      "answerText": "~4000 г. до н. э.",
                      "answerIsCorrect": false,
            "userAnswer": false                    
          },
          {
            "answerText": "~5000 г. до н. э.",
            "answerIsCorrect": false,
            "userAnswer": true                    
          }
        ]
          }
      ],
      "score": 200
  
  }

  constructor() { }

}
