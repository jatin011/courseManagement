import { validateHorizontalPosition } from '@angular/cdk/overlay';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { LoginServiceService } from 'src/app/login/login-service.service';
import { User } from 'src/app/Model/User';
import { CoursesServiceService } from '../services/courses-service.service';
import { SkillServiceService } from '../services/skill-service.service';

export interface Skill {
  skillName:String,
  skillId:Number
}


@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.scss']
})
export class AddCourseComponent implements OnInit {

  currentUserData:User;

  addCourseFormGroup:FormGroup;
  skillsSelected:Skill[]=new Array();
  myControl = new FormControl();
  options:any = [];
  filteredOptions: Observable<Skill[]>;

  fileData:File;


  constructor(private loginService:LoginServiceService,
    private courseService:CoursesServiceService,
    private skillService:SkillServiceService,
    private _snackbar:MatSnackBar) { }

  ngOnInit(): void {
    this.currentUserData=this.loginService.getUser();
    if(this.currentUserData==null)
    {
      this.loginService.signOut();
    }

    this.skillService.getAllSkill().subscribe(data=>{
      this.options=data;
      console.log(data)
      this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => typeof value === 'string' ? value : value.name),
        map(name => name ? this._filter(name) : this.options.slice())
      );
      console.log(this.options)
    })


    const urlRegex = /^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&'\(\)\*\+,;=.]+$/;

      this.addCourseFormGroup=new FormGroup(
        {
          userId:new FormControl({value:this.currentUserData.userId,disabled:true}),
          courseName:new FormControl('',Validators.required),
          courseDescription:new FormControl('',Validators.required),
          imageUrl:new FormControl('',[Validators.required,Validators.pattern(urlRegex)]),
          preRequisite:new FormControl('',Validators.required),
          materialDescription:new FormControl('',Validators.required),
          fileData:new FormControl()
        }
      )


  }

  //899 217 015
//kt6u28

  uploadfile(event)
  {
    console.log(event.target.files)
    this.fileData=event.target.files
    console.log(this.fileData)
  }

  validate()
  {
    return this.addCourseFormGroup.get("courseName").status=="VALID"
      && this.addCourseFormGroup.get("courseDescription").status=="VALID"
      && this.addCourseFormGroup.get("preRequisite").status=="VALID"
      && this.addCourseFormGroup.get("imageUrl").status=="VALID"
      && this.addCourseFormGroup.get("materialDescription").status=="VALID"
      && this.fileData!=null;

  }

  addCourse(){

    if(this.validate())
    {
      let courseData={
        "userId":this.addCourseFormGroup.get("userId").value,
        "courseName":this.addCourseFormGroup.get("courseName").value,
        "courseDescription":this.addCourseFormGroup.get("courseDescription").value,
        "preRequisites":this.addCourseFormGroup.get("preRequisite").value,
        "imageUrl":this.addCourseFormGroup.get("imageUrl").value
      }
      let trainingMaterial={
        "materialDescription":this.addCourseFormGroup.get("materialDescription").value,
        "file":this.fileData
      }

      this.courseService.addnewCourse(courseData,this.skillsSelected,trainingMaterial);
    }
    else{
      this._snackbar.open("Invalid Course Details","Ok",{duration:3000})
    }
  }

  displayFn(skill): string {
    this.skillsSelected.push(skill);
    this.options=this.options.filter((currentSkill)=> {return currentSkill.skillId!==skill.skillId;} );
    return  '';
  }

  displayFnWrapper()
  {
    return (skill) => this.displayFn(skill);
  }

  private _filter(name: string): Skill[] {
    const filterValue = name.toLowerCase();
    return this.options.filter(option => option.skillName.toLowerCase().indexOf(filterValue) === 0);
  }

}
