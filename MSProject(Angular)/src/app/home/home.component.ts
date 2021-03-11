import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from '../login/login-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  user:any;
  constructor(private loginService:LoginServiceService,private route:Router) { }
  ngOnInit(): void {
    this.user=this.loginService.getUser();
    if(this.user==null)
    {
      this.route.navigate([""]);
    }
  }

  signOut():void
  {
    this.loginService.signOut();
  }
}
