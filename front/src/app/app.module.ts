import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { ProfileComponent } from './profile/profile.component';

import { authInterceptorProviders } from './_helpers/auth.interceptor';
import {QuizSetupComponent} from "./quiz-setup/quiz-setup.component";
import {QandAComponent} from "./q-and-a/q-and-a.component";
import {questionStorageService} from "./_services/questionStorage.Service";
import {AuthService} from "./_services/auth.service";
import {AuthGuard} from "./_services/auth.guard";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    BoardAdminComponent,
    ProfileComponent,
    QuizSetupComponent,
    QandAComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders, questionStorageService, AuthService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
