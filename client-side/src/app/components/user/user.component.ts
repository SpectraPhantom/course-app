import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/Course';
import { CourseService } from 'src/app/services/course.service';
import {Enrollment} from 'src/app/models/Enrollment';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  courseList:Array<Course>
  errorMessage:String
  infoMessage:String

  constructor(private router:Router,private courseService:CourseService) { }

  ngOnInit(): void {
    this.findCourses();
  }

  getLogin(){
    let jwt=window.sessionStorage.getItem('token');
    let jwtData = jwt.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);
    let username = decodedJwtData.user_name;
    return username;
  }

  signOut(){
    this.router.navigate([""]);
  }
  
  findCourses(){
    this.courseService.getCourses().subscribe(data=>{
      this.courseList=data;
    })
  }

  enroll(course:Course){
    var enrollment=new Enrollment();
    enrollment.course=course;
    enrollment.userName=this.getLogin();

    this.courseService.enroll(enrollment).subscribe(data=>{
      this.infoMessage="Successfully enrolled!";
    },err=>{
      this.errorMessage="Something goes wrong ;(";
    });
  }

}
