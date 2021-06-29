import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from '../models/Course';
import { Enrollment } from '../models/Enrollment';

const API_URL="http://localhost:8080/courseapi/courses";

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http:HttpClient) { }

  getCourses(): Observable<any>{
    return this.http.get(API_URL+'?access_token='+JSON.parse(window.sessionStorage.getItem('token')).access_token);
  }

  enroll(enrollment:Enrollment):Observable<any>{
    return this.http.post(API_URL + '/enroll'+'?access_token='+JSON.parse(window.sessionStorage.getItem('token')).access_token,enrollment);
  }

  findStudentsOfCourse(courseId:number):Observable<any>{
    return this.http.get(API_URL + '/'+courseId+'?access_token='+JSON.parse(window.sessionStorage.getItem('token')).access_token);
  }

  addCourse(course:Course):Observable<Course>{
    return this.http.post<Course>(API_URL+'?access_token='+JSON.parse(window.sessionStorage.getItem('token')).access_token,course);
  }

}
