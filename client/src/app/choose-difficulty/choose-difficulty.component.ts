import { Component, OnInit } from '@angular/core';
import { Diffs } from './Diffs';
import { DiffService } from './diff.service';

@Component({
  selector: 'app-choose-difficulty',
  templateUrl: './choose-difficulty.component.html',
  styleUrls: ['./choose-difficulty.component.css']
})
export class ChooseDifficultyComponent implements OnInit {
  diffs: Diffs[];

  constructor(private diffService: DiffService) { }

  ngOnInit(): void {
    this.getDiffs();
  }

  getDiffs(): void {
    this.diffService.getDiffs()
      .subscribe(diffs => this.diffs = diffs);
  }

}
