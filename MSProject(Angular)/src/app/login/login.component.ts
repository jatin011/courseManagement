import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SocialUser } from 'angularx-social-login';
import { SocialAuthService } from 'angularx-social-login';
import { GoogleLoginProvider } from "angularx-social-login";
import { User } from '../Model/User';
import { LoginServiceService } from './login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username:String="";
  password:String="";
  user:User;

  constructor(private loginService:LoginServiceService,private router:Router) { }

  ngOnInit(): void {
    if(this.loginService.getUser()!=null)
    {
      this.redirectToHome();
    }
  }

  loginWithUsername(){
    console.log(this.loginService.getUser())
  }

  redirectToHome()
  {
    this.router.navigate(["/home"]);
  }

   loginWithGoogle(){
    this.loginService.loginByGoogle();
  }
}
