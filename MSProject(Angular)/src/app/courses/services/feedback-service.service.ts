import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FeedbackServiceService {

  constructor(private httpClient:HttpClient) { }


  getFeedback(courseId)
  {
    return this.httpClient.get("http://localhost:8090/feedback/all/"+courseId);
  }

}
