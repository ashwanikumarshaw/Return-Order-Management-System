import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthVarService } from '../auth-var.service';
import { RestapiService } from '../restapi.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new User();
  msg = '';
  constructor(private service: RestapiService, private router: Router, private authVar: AuthVarService) { }

  ngOnInit(): void {
  }

  loginUser() {
    this.service.login(this.user).subscribe(
      response => {
        console.log("Successfully login" + response);
        this.authVar.token = response.jwtToken;
        this.authVar.username = response.user.userName;
        sessionStorage.setItem('token', response.jwtToken);
        sessionStorage.setItem('username', response.user.userName);

        this.router.navigate(['/home'], {
          state: {
            data: { "token": response.jwtToken, "userName": this.user.userName }
          }
        });
      },
      error => {
        console.log("Exception Occured" + error)
        this.msg = "invalid credentials. please try again";
      }
    );
  }


}
