import { Component, Input, OnInit } from '@angular/core';
import { Course } from '../Model/Course';
import { CoursesServiceService } from './services/courses-service.service';
import { FeedbackServiceService } from './services/feedback-service.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  sortBy;

  searchText:String="";
  allCourse:any=new Array();
  averageRatings:Number[]=[];
  feedbacks:any=[];
  constructor(private courseService :CoursesServiceService,private feedbackService:FeedbackServiceService) { }

 ngOnInit(): void {
   this.courseService.getAllCourse();
    this.courseService.obs.subscribe(data=>{
      this.allCourse=data;
      //console.log(data)
      for(let i=0;i<this.allCourse.length;i++)
      {
        let rating=0;
        let feedback:any=[]
        this.feedbackService.getFeedback(this.allCourse[i].courseId).subscribe(feedbacks=>{
          feedback=feedbacks;
          feedback.filter(data=>{
            rating=rating+data.rating;
          })
          this.allCourse[i].averageRating=rating/feedback.length;
          this.allCourse[i].numberFeedback=feedback.length;
        })
      }
    })
  }

  sortByFeedback(){
    let allCourses=[]
    for(let course of this.allCourse)
    {
      allCourses.push(course)
    }
    allCourses.sort(function(a,b){
      return b.numberFeedback-a.numberFeedback;
    })
    this.allCourse=allCourses

  }
  sortByRating(){
    let allCourses=[]
    for(let course of this.allCourse)
    {
      allCourses.push(course)
    }
    allCourses.sort(function(a,b){
      return b.averageRating-a.averageRating;
    })
    this.allCourse=allCourses
  }

  onChangeSearchText(event)
  {
    console.log(this.searchText)
    this.allCourse=this.courseService.getFilteredcourse(this.searchText);
  }




}
