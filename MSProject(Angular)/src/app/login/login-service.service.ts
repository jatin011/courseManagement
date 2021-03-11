import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { GoogleLoginProvider, SocialAuthService, SocialUser } from 'angularx-social-login';
import { BehaviorSubject } from 'rxjs';
import { User } from '../Model/User';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  user:any;
  constructor(private authService:SocialAuthService,private httpClient:HttpClient,private route:Router) { }

   loginByGoogle()
  {
     this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then( (response:SocialUser)=>{
      let currentUser={
        "firstName":response.firstName,
        "lastName":response.lastName,
        "emailId":response.email
      }
       this.httpClient.post("http://localhost:8090/login/googleLogin",currentUser).subscribe(response1=>{
        this.user=response1;
        localStorage.setItem("userData",JSON.stringify(response1));
        this.route.navigate(["/home"]);
      })

    }).catch(err=>{
      console.log(err)
    })
  }

   getUser():User
  {
    this.user=JSON.parse(localStorage.getItem("userData"));
    console.log(this.user)
    return this.user;
  }

  signOut():void
  {
    this.authService.signOut().catch(err=>{
      //console.log(err)
    });
    this.user=null;
    localStorage.removeItem("userData");
    this.route.navigate([""]);
  }


  getAllUser()
  {
    return this.httpClient.get("http://localhost:8090/login/getAll");
  }
}
