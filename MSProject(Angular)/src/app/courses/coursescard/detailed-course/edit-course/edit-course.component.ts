import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CoursesServiceService } from 'src/app/courses/services/courses-service.service';

@Component({
  selector: 'app-edit-course',
  templateUrl: './edit-course.component.html',
  styleUrls: ['./edit-course.component.scss']
})
export class EditCourseComponent implements OnInit {

  editCourseFormGroup:FormGroup;

  course:any;
  constructor(private courseService:CoursesServiceService,private activatedroute:ActivatedRoute,private router:Router,
    private _snackbar:MatSnackBar) { }

  ngOnInit(): void {
    this.activatedroute.paramMap.subscribe(param=>{
      this.course=JSON.parse(param['params']['course']);
      console.log(this.course)
    })

    this.editCourseFormGroup=new FormGroup({
      courseName:new FormControl(this.course.courseName,Validators.required),
      courseId:new FormControl({value:this.course.courseId,disabled:true}),
      userId:new FormControl({value:this.course.userId,disabled:true}),
      courseDescription:new FormControl(this.course.courseDescription,Validators.required),
      preRequisites:new FormControl(this.course.preRequisite,Validators.required),
      imageUrl:new FormControl(this.course.imageUrl,Validators.required)
    })
  }

  editCourse()
  {
    let newCourse=this.course;
    newCourse.courseName=this.editCourseFormGroup.get('courseName').value;
    newCourse.courseDescription=this.editCourseFormGroup.get('courseDescription').value
    newCourse.preRequisite=this.editCourseFormGroup.get('preRequisites').value
    newCourse.imageUrl=this.editCourseFormGroup.get('imageUrl').value
    this.courseService.updateCourse(newCourse);
    this.router.navigate(["../course",this.course.courseId],{relativeTo:this.activatedroute});
  }

  cancel()
  {
    this._snackbar.open("Redirected To Course","Ok",{duration:2000});
    this.router.navigate(["/course",this.course.courseId]);
  }


}
