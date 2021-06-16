import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Enrollment } from '../models/Enrollment';

const API_URL="http://localhost:8080/courseapi/courses";

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http:HttpClient) { }

  getCourses(): Observable<any>{
    return this.http.get(API_URL,{headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }

  enroll(enrollment:Enrollment):Observable<any>{
    return this.http.post(API_URL + '/enroll',JSON.stringify(enrollment),
    {headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }

  findStudentsOfCourse(courseId:number):Observable<any>{
    return this.http.get(API_URL + '/'+courseId,
    {headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }

}
