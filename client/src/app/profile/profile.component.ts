import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  private isStatisticActive: boolean = true;
  private isHistoryActive: boolean = false;
  constructor(
   
    ) { }

  ngOnInit() {
   
  }

  showStatistic() {
    if (!this.isStatisticActive){
      this.isStatisticActive = true;
      this.isHistoryActive = false;
    }
  }
 
  showHistory() {
    if (!this.isHistoryActive){
      this.isStatisticActive = false;
      this.isHistoryActive = true;
    }
  }

}




