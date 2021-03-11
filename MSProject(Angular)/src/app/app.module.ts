import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { SocialAuthService, SocialLoginModule } from 'angularx-social-login';
import { SocialAuthServiceConfig, GoogleLoginProvider  } from 'angularx-social-login';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import {MatToolbarModule} from "@angular/material/toolbar";
import { HeaderComponent } from './partials/header/header.component';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { CoursesComponent } from './courses/courses.component';
import { CoursescardComponent } from './courses/coursescard/coursescard.component'
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import { AddCourseComponent } from './courses/add-course/add-course.component';
import {MatInputModule} from '@angular/material/input';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { CommonModule } from '@angular/common';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { DetailedCourseComponent } from './courses/coursescard/detailed-course/detailed-course.component';
import { FeedbackCardComponent } from './courses/coursescard/detailed-course/feedback-card/feedback-card.component';
import { EditCourseComponent } from './courses/coursescard/detailed-course/edit-course/edit-course.component';
import { RatingModule } from 'ng-starrating';
import { TrendsComponent } from './trends/trends.component';
import {ChartsModule} from 'ng2-charts';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    CoursesComponent,
    CoursescardComponent,
    AddCourseComponent,
    DetailedCourseComponent,
    FeedbackCardComponent,
    EditCourseComponent,
    TrendsComponent
  ],
  imports: [
    BrowserModule,
    RatingModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    SocialLoginModule,
    AppRoutingModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule,
    BrowserAnimationsModule,
    ChartsModule
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '1062221634741-6t58jdnhsi80bi07a9abe5q6q0m6gndr.apps.googleusercontent.com'
            )
          }
        ]
        } as SocialAuthServiceConfig
      }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
