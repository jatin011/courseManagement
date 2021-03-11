import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AddCourseComponent } from './courses/add-course/add-course.component';
import { DetailedCourseComponent } from './courses/coursescard/detailed-course/detailed-course.component';
import { EditCourseComponent } from './courses/coursescard/detailed-course/edit-course/edit-course.component';
import { TrendsComponent } from './trends/trends.component';


const routes:Routes=[
  {path:'login',component:LoginComponent},
  {path:'home',component:HomeComponent},
  {path:'addCourses',component:AddCourseComponent},
  {path:'editCourse',component:EditCourseComponent},
  {path:'course/:courseId',component:DetailedCourseComponent},
  {path:'trends',component:TrendsComponent},
  {path:'' , redirectTo :"login",pathMatch:"full"}
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
