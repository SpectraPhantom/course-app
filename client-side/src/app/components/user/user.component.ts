import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/Course';
import { CourseService } from 'src/app/services/course.service';
import { Enrollment } from 'src/app/models/Enrollment';
import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from 'src/app/services/user.service';
import { Meta } from '@angular/platform-browser';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  courseList: Array<Course>;
  userId: Number;
  searchValue: string;

  constructor(private router: Router, private courseService: CourseService, private userService: UserService, private meta: Meta) {
    this.meta.addTag({ name: "viewport", content: "width=device-width, initial-scale=1, shrink-to-fit=no" })
  }

  ngOnInit(): void {
    this.findCourses();
    this.getUserId();
  }

  signOut() {
    this.router.navigate([""]);
  }

  findCourses() {
    this.courseService.getCourses().subscribe(data => {
      this.courseList = data;
    })
  }

  getUserId() {
    this.userService.getUserInfo().subscribe(data => {
      console.log(data.id);
      this.userId = data.id;
    })
  }

  getLogin() {
    let jwt = window.sessionStorage.getItem('token');
    let jwtData = jwt.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);
    let username = decodedJwtData.user_name;
    return username;
  }

  enroll(course: Course) {
    var enrollment = new Enrollment();
    enrollment.course = course;
    enrollment.userName = this.getLogin();
    enrollment.userId = this.userId;

    this.courseService.enroll(enrollment).subscribe(data => {
      alert("Successfully enrolled!");
    },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

}
