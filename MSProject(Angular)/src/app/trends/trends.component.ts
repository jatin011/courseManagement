import { Component, OnInit,ViewChild } from '@angular/core';
import { CoursesServiceService } from '../courses/services/courses-service.service';
import { ChartOptions, ChartType, ChartDataSets } from "chart.js";
import { Label } from "ng2-charts";
import { FeedbackServiceService } from '../courses/services/feedback-service.service';
import { LoginServiceService } from '../login/login-service.service';

@Component({
  selector: 'app-trends',
  templateUrl: './trends.component.html',
  styleUrls: ['./trends.component.scss']
})
export class TrendsComponent implements OnInit {

  public ratingBarChartOptions: ChartOptions = {
    responsive: true
  };
  public ratingBarChartLabels: Label[];
  public barChartType: ChartType = "bar";
  public barChartLegend = true;
  public barChartPlugins = [];

  allCourse:any=[];
  public ratingBarChartData: ChartDataSets[];

  public feedbackBarChartOptions: ChartOptions = {
    responsive: true
  };
  public feedbackBarChartLabels: Label[];
  public feedbackBarChartData: ChartDataSets[];


  public userLocationBarChartOptions: ChartOptions = {
    responsive: true
  };
  public userLocationBarChartLabels: Label[];
  public userLocationBarChartData: ChartDataSets[];


  courseNames:any=[]
  averageRating:any=[]
  numberFeedbacks:any=[]
  locations:any=[]
  locationNumber:any=[]



  ngOnInit(){


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
          this.courseNames.push(this.allCourse[i].courseName)
          this.averageRating.push(this.allCourse[i].averageRating);
          this.numberFeedbacks.push(feedback.length);
        })
      }



      this.loginService.getAllUser().subscribe(data=>{
        let users:any=[];
        users=data;
        for(let i=0;i<users.length;i++)
        {
          if(this.locations.indexOf(users[i].location)==-1)
          {
            this.locations.push(users[i].location)
            this.locationNumber.push(1);
          }
          else{
            let index=this.locations.indexOf(users[i].location)
            this.locationNumber[index]+=1;
          }
        }
        console.log(this.locations)
        console.log(this.locationNumber)

      })

      this.ratingBarChartLabels=this.courseNames
      this.ratingBarChartData= [
        { data: this.averageRating, label:" Average Ratings " }
      ];
      this.feedbackBarChartLabels=this.courseNames;
      this.feedbackBarChartData=[
        {data:this.numberFeedbacks,label:"Number Of Feedbacks"}
      ]
      this.userLocationBarChartLabels=this.locations
      this.userLocationBarChartData=[
        {data:this.locationNumber,label:"locations Data"}
      ]
    })


  }

  constructor(private courseService:CoursesServiceService,private feedbackService:FeedbackServiceService ,private loginService:LoginServiceService) {
  }
}
