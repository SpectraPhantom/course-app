import { Component, OnInit, TemplateRef } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/User';
import { HttpErrorResponse } from '@angular/common/http';
import { Role } from 'src/app/models/Role';
import { CourseService } from 'src/app/services/course.service';
import { Course } from 'src/app/models/Course';
import flatpickr from "flatpickr";
import { Meta } from '@angular/platform-browser';
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  userForm: FormGroup;
  courseForm: FormGroup;
  courseList: Array<Course>;
  todayDate: Date;
  userList:Array<String>;

  role = Role;
  keys(): Array<string> {
    var keys = Object.keys(this.role);
    return keys.slice(keys.length / 2);
  }

  constructor(private formBuilder: FormBuilder, private router: Router, private userService: UserService, 
    private courseService: CourseService,private meta:Meta) { 
      this.meta.addTag({name: "viewport",content: "width=device-width, initial-scale=1, shrink-to-fit=no"})
    }

  addUser() {
    console.log(this.userForm.value);
    if (this.userForm.invalid) {
      return;
    }
    this.userService.addUser(this.userForm.value).subscribe(
      (response: User) => {
        console.log(response);
        alert("User added!");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  addCourse() {
    console.log(this.courseForm.value);
    var datesAsString=this.courseForm.controls['termList'].value;
    datesAsString=datesAsString.replace(" ","").split(",");
    console.log(datesAsString);
    this.courseForm.controls['termList'].setValue(datesAsString);
    if (this.courseForm.invalid) {
      return;
    }
    this.courseService.addCourse(this.courseForm.value).subscribe(
      (response: Course) => {
        console.log(response);
        alert("Course added!");
        this.findCourses();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  findCourses() {
    this.courseService.getCourses().subscribe(data => {
      this.courseList = data;
    })
  }

  findStudentsOfCourse(courseId: number){
    this.courseService.findStudentsOfCourse(courseId).subscribe(data=>{
      this.userList=data;
    })
  }

  signOut() {
    this.router.navigate([""]);
  }

  ngOnInit() {

    flatpickr("#termList", {
      minDate: "today",
      mode: "multiple",
    });

    this.userForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required])],
      password: ['', Validators.required],
      role: ['', Validators.required]
    });

    this.courseForm = this.formBuilder.group({
      name: ['', Validators.compose([Validators.required])],
      trainerName: ['', Validators.required],
      termList: ['', Validators.required]
    });

    this.findCourses();
  }
}
