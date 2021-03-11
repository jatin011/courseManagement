import { HttpClient } from '@angular/common/http';
import { isDefined } from '@angular/compiler/src/util';
import { Injectable, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { Course } from 'src/app/Model/Course';
import { Skill } from '../add-course/add-course.component';

@Injectable({
  providedIn: 'root'
})
export class CoursesServiceService {

  allCourses:any=[];

  allcourseDataBS=new BehaviorSubject({});
  obs=this.allcourseDataBS.asObservable();

  constructor(private httpClient:HttpClient,private _snackBar:MatSnackBar,private route:Router) { }

  async getAllCourse(){
    await this.httpClient.get("http://localhost:8090/courses/all").subscribe(data=>{
      this.allCourses=data;
      this.allcourseDataBS.next(data);
    })
  }

  async addnewCourse(course,skills:Skill[],trainingMaterial){
    await this.httpClient.post("http://localhost:8090/courses/addCourse",course).subscribe(response=>{

      let newCourse:any=response;
      this.allCourses.push(newCourse);
      for(let i=0;i<skills.length;i++)
      {
        let skillInput=new FormData();
        skillInput.append("skillId",String(skills[i].skillId));
        skillInput.append("courseId",String(newCourse.courseId));
        this.httpClient.post("http://localhost:8090/courses/addSkillInCourse",skillInput).subscribe(
          data=>{
             //console.log(data);
          },(error)=>{
            this._snackBar.open("Cannot create course,Try Later!!","Ok",{duration:2000});
            this.route.navigate(["/home"]);
            return;
          }
        );
      }

      let trainingInput=new FormData();
      trainingInput.append("materialDescription",trainingMaterial.materialDescription);
      for(let file of trainingMaterial.file)
      {
        trainingInput.append("file",file)
      }
      trainingInput.append("courseId",newCourse.courseId);
      this.httpClient.post("http://localhost:8090/material/addMaterial",trainingInput).subscribe(reponse=>{
        //console.log(response)
      },(error)=>{
        this._snackBar.open("Cannot create course,Try Later!!","Ok",{duration:2000});
        this.route.navigate(["/home"]);
        return;
      });
      this._snackBar.open("New Course "+newCourse.courseName+" Created","Ok",{duration:2000});
      this.route.navigate(["/home"]);
    },(error)=>{
      this._snackBar.open("Cannot create course,Try Later!!","Ok",{duration:2000});
      this.route.navigate(["/home"]);
      return;
    });

  }

  getCourse(courseId)
  {
    return this.httpClient.get("http://localhost:8090/courses/getById/"+courseId);
  }


    deleteCourse(courseId)
   {

      return this.httpClient.delete("http://localhost:8090/courses/deleteCourse/"+courseId);
   }

   removeCourseFromList(courseId){
     this.allCourses=this.allCourses.filter(function(data){return data.courseId!==courseId});
     this.allcourseDataBS.next(this.allCourses)
   }


  getFilteredcourse(searchOn)
  {
    return this.allCourses.filter(function(data){
      return data.courseName.startsWith(searchOn) || data.courseDescription.startsWith(searchOn)
    })
  }


  updateCourse(newCourse)
  {
    this.httpClient.put("http://localhost:8090/courses/updateCourse/"+newCourse.courseId,newCourse).subscribe(data=>{
    console.log(data)
    this._snackBar.open("Course Edited","Ok",{duration:2000})
    },(err)=>{
      this._snackBar.open("CourseCannot be edited Now. Try Later!!","Ok",{duration:2000});
    })
  }




}
