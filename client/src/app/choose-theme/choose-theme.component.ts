import { Component, OnInit } from '@angular/core';
import { Themes } from './Themes';
import {ThemeService} from './theme.service';

@Component({
  selector: 'app-choose-theme',
  templateUrl: './choose-theme.component.html',
  styleUrls: ['./choose-theme.component.css']
})
export class ChooseThemeComponent implements OnInit {
  themes: Themes[] ;

  constructor(private themeService: ThemeService) { }

  ngOnInit(): void {
    this.getThemes();
  }

  getThemes(): void {
    this.themeService.getThemes()
      .subscribe(themes => this.themes = themes);
  }
}
