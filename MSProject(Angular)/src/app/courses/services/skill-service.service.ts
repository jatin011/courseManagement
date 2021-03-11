import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SkillServiceService {

  constructor(private httpClient:HttpClient) { }

  getAllSkill(){
    return this.httpClient.get("http://localhost:8090/courses/getSkills");
  }

  getSkillByCourseId(courseId)
  {
    return this.httpClient.get("http://localhost:8090/courses/getAllSkills/"+courseId);
  }
}
