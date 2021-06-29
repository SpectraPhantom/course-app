import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';

const API_URL="http://localhost:8080/userapi/users";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  addUser(user:User):Observable<User>{
    return this.http.post<User>(API_URL+'?access_token='+JSON.parse(window.sessionStorage.getItem('token')).access_token,user);
  }

  getLogin(){
    let jwt=window.sessionStorage.getItem('token');
    let jwtData = jwt.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);
    let username = decodedJwtData.user_name;
    return username;
  }

  getUserInfo():Observable<any>{
    return this.http.get(API_URL+'/'+this.getLogin()+'?access_token='+JSON.parse(window.sessionStorage.getItem('token')).access_token);
  }

}
