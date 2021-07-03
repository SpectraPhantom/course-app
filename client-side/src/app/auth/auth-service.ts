import { Injectable } from "@angular/core";
import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable()
export class AuthService {

    constructor() { }

    public isAuthenticated(): boolean {

        const isToken=sessionStorage.getItem('token');

        if(isToken!=null){

        const token = JSON.parse(sessionStorage.getItem('token')).access_token;

        const helper = new JwtHelperService();

        return !helper.isTokenExpired(token);
        }
        return false;
    }
}
