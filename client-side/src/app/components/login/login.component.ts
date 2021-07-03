import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Meta } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  loginForm: FormGroup;
  invalidLogin: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService, private meta: Meta) {
    this.meta.addTag({ name: "viewport", content: "width=device-width, initial-scale=1, shrink-to-fit=no" })
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
    const body = new HttpParams()
      .set('scope', 'webclient')
      .set('grant_type', 'password')
      .set('username', this.loginForm.controls.username.value)
      .set('password', this.loginForm.controls.password.value);



    this.loginService.login(body.toString()).subscribe(data => {
      window.sessionStorage.setItem('token', JSON.stringify(data));
      let jwt = window.sessionStorage.getItem('token');
      let jwtData = jwt.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      let decodedJwtData = JSON.parse(decodedJwtJsonData);
      let isAdmin = decodedJwtData.authorities;

      if (isAdmin == 'USER') {
        this.router.navigate(["/user"]);
      }
      else {
        this.router.navigate(["/admin"]);
      }
    }, error => {
      alert(error.error.error_description)
    });
  }

  ngOnInit() {
    window.sessionStorage.removeItem('token');
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required])],
      password: ['', Validators.required]
    });
  }
}
