import { Component } from '@angular/core';
import { SocialUser } from 'angularx-social-login';
import { LoginServiceService } from './login/login-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  user:SocialUser;
  constructor(private loginService:LoginServiceService){}

  ngOnInit()
  {
  }

}
