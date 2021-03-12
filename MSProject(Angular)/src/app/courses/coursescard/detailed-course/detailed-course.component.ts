import { ElementRef, ViewChild } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CoursesServiceService } from '../../services/courses-service.service';
import { FeedbackServiceService } from '../../services/feedback-service.service';
import { MaterialServiceService } from '../../services/material-service.service';
import { SkillServiceService } from '../../services/skill-service.service';

@Component({
  selector: 'app-detailed-course',
  templateUrl: './detailed-course.component.html',
  styleUrls: ['./detailed-course.component.scss']
})
export class DetailedCourseComponent implements OnInit {


  newFile:File;

  @ViewChild('materialDescriptionInput') materialDescriptionInput:ElementRef;

  isEditable:boolean[]=[];
 isPreviousVisible:boolean[]=[];

  feedbacks:any=[{
    participantName:"Jatin",
    feedbackText:"Was a very Good course",
    createdat:Date.now
  }]

  skills:any=[]
  course:any;
  addMaterialBool=false;

  newMaterialDescription="";
  previousVersions:any=[];

  materials:any;

  constructor(private materialService:MaterialServiceService,
    private courseService:CoursesServiceService,
    private activeRoute:ActivatedRoute,
    private router:Router,
    private feedbackService:FeedbackServiceService,
    private _snackBar:MatSnackBar,
    private skillService:SkillServiceService) { }

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(param=>{
      this.courseService.getCourse(param['params']['courseId']).subscribe( async course=>{
        this.course=course;
        console.log(this.course)
        await this.materialService.getMaterial(this.course.courseId).subscribe(material=>{
          this.materials=material;

        },err=>{
          console.log(err)
        })
        this.skillService.getSkillByCourseId(this.course.courseId).subscribe(skills=>{
          this.skills=skills;
        })
        this.feedbackService.getFeedback(this.course.courseId).subscribe(feedbacks=>{
          this.feedbacks=feedbacks;
        },(err)=>{
          console.log(err);
        })
      },(err)=>{
        console.log(err);
      })
    })
  }


s


  editCourse()
  {
    this.router.navigate(["../../editCourse/",{course:JSON.stringify(this.course)}],{relativeTo:this.activeRoute});
  }

  uploadNewFile(event)
  {
    this.newFile=event.target.files[0];
  }

  editMaterial(i)
  {
    this.isEditable[i]=true;
  }

  doneEditing(material,i)
  {
    let newMaterial={
      "materialId":material.materialId,
      "courseId":this.course.courseId,
      "materialDescription":material.materialDescription,
      "fileData":this.newFile
    }
    this.materialService.updateMaterial(newMaterial).subscribe(newMaterial=>{
      this.materials=this.materials.filter(materialData=>{return materialData.materialId!=material.materialId});
      this.materials.push(newMaterial);
      this._snackBar.open("Updated Material Succesfully","Ok",{duration:2000});
    },(err)=>{
      this._snackBar.open("Material Update Unsuccessfull. Try Later!!!","Ok",{duration:2000});
    });
    this.isEditable[i]=false
  }

  cancelEditing(i)
  {
    this.isEditable[i]=false;
  }


  getPreviousVersions(i,materialId)
  {
    this.isPreviousVisible[i]=true;
    this.materialService.getPreviousVersions(this.course.courseId,materialId).subscribe(previous=>{
      this.materials[i].previousVersions=previous;
      this.materials[i].previousVersions=this.materials[i].previousVersions.filter(data=>{
        return data.materialId!=materialId;
      })
    })
  }



    base64ToArrayBuffer(base64) {
      const binaryString = window.atob(base64);
      const len = binaryString.length;
      const bytes = new Uint8Array(len);
      for (let i = 0; i < len; i++) {
        bytes[i] = binaryString.charCodeAt(i);
      }
      return bytes.buffer;
    }

    downloadFile(data, fileType) {
      const byteArray = this.base64ToArrayBuffer(data);
      const blob = new Blob([byteArray], { type: fileType });
      const url = window.URL.createObjectURL(blob);
      window.open(url);
    }



    deleteMaterial(materialId)
    {
      this.materialService.deleteService(materialId).subscribe(response=>{
        this._snackBar.open("Deleted Material","Ok",{duration:2000});
         this.materialService.getMaterial(this.course.courseId).subscribe(material=>{
          this.materials=material;
        },err=>{
          console.log(err)
        })
      },err=>{
        this._snackBar.open("Cannot Delete Now","Ok",{duration:2000});
      })
    }


    addNewMaterial()
    {
      this.materialService.addNewMaterial(this.course.courseId,this.newMaterialDescription,this.newFile).subscribe(response=>{
        let addedMaterial=response;
        this.materials.push(addedMaterial[0])
      });
      this.newMaterialDescription="";
      this.newFile=null;
      this.addMaterialBool=false;
    }
}
