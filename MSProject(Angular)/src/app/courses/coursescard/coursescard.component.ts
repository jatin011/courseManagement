import { isDefined } from '@angular/compiler/src/util';
import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CoursesServiceService } from '../services/courses-service.service';
import { FeedbackServiceService } from '../services/feedback-service.service';

@Component({
  selector: 'app-coursescard',
  templateUrl: './coursescard.component.html',
  styleUrls: ['./coursescard.component.scss']
})
export class CoursescardComponent implements OnInit {

  stars: number[] = [1, 2, 3, 4, 5];
  @Input()
  averageRating: number;


  @Input()
  currentCourse;

  constructor(private courseService:CoursesServiceService,private _matSnackBar:MatSnackBar,private router:Router,
    private feedbackService:FeedbackServiceService) { }

  ngOnInit(): void {

  }

  deleteCourse()
  {

    this.courseService.deleteCourse(this.currentCourse.courseId).subscribe((data)=>{
      let isDeleted=data;
      if(isDeleted)
      {
        this.courseService.removeCourseFromList(this.currentCourse.courseId);
        this._matSnackBar.open("Course "+this.currentCourse.courseName+" was deleted successfully","Ok",{duration:2000});
      }
      else{
        this._matSnackBar.open("Delete was unsuccessfully","Ok",{duration:2000});
      }
    });
  }

  viewCourse(courseId)
  {
    this.router.navigate(["course",courseId]);
  }

}
