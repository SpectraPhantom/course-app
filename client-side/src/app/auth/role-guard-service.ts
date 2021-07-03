import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router } from "@angular/router";
import { AuthService } from "./auth-service";


@Injectable()
export class RoleGuardService implements CanActivate {

    constructor(public auth: AuthService, public router: Router) { }


    getAuthorities() {
        let jwt = window.sessionStorage.getItem('token');
        let jwtData = jwt.split('.')[1];
        let decodedJwtJsonData = window.atob(jwtData);
        let decodedJwtData = JSON.parse(decodedJwtJsonData);
        let isAdmin = decodedJwtData.authorities;

        return isAdmin;

    }

    canActivate(route: ActivatedRouteSnapshot): boolean {

        const expectedRole = route.data.expectedRole;

        if (!this.auth.isAuthenticated() || this.getAuthorities().toString() !== expectedRole) {
            this.router.navigate(['login']);
            return false;
        }
        return true;
    }
}
