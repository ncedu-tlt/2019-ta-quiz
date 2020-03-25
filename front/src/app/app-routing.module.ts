import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import {QuizSetupComponent} from "./quiz-setup/quiz-setup.component";
import {QandAComponent} from "./q-and-a/q-and-a.component";
import {AuthGuard} from "./_services/auth.guard";
import {ResultComponent} from "./result/result.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'quiz-setup', component: QuizSetupComponent, canActivate: [AuthGuard] } ,
  { path: 'qanda', component: QandAComponent, canActivate: [AuthGuard] } ,
  // { path: 'result', component: ResultComponent, canActivate: [AuthGuard] } ,
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
