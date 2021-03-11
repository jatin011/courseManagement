import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginServiceService } from 'src/app/login/login-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLoggedIn:Boolean=false;
  constructor(private loginService:LoginServiceService,private router:Router) { }

  ngOnInit(): void {
    if(this.loginService.getUser()!=null){
      this.isLoggedIn=true;
    }
  }


  redirectToTrends(){
    this.router.navigate(["/trends"]);
  }

  redirectToAddCourse(){
    this.router.navigate(["/addCourses"]);
  }

  redirectToHome()
  {
    this.router.navigate(['/home'])
  }
  signOut()
  {
    this.loginService.signOut();
  }

}
