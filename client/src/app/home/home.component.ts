import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  logoWindow:string = "assets/images/main.png";

  constructor(
    private userService: UserService,
    private router: Router,
    ) { }

  ngOnInit() { }

  goToStart() {
    this.router.navigateByUrl('quiz-setup');
  }
}
