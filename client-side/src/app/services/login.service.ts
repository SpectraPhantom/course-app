import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(loginPayload: any) {
    const headers = {
      'Authorization': 'Basic ' + btoa('clientapp:password'),
      'Content-type': 'application/x-www-form-urlencoded'
    }
    return this.http.post('http://localhost:8901/' + 'oauth/token', loginPayload, {headers});
  }
}
