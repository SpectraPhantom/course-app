import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './components/admin/admin.component';
import { LoginComponent } from './components/login/login.component';
import { UserComponent } from './components/user/user.component';
import { AuthGuardService as AuthGuard } from './auth/auth-guard-service';
import { RoleGuardService as RoleGuard } from './auth/role-guard-service';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'user', component: UserComponent, canActivate: [AuthGuard,RoleGuard], data: { expectedRole: 'USER' } },
  { path: 'admin', component: AdminComponent, canActivate: [AuthGuard,RoleGuard], data: { expectedRole: 'ADMIN' } },
  { path: '', component: LoginComponent },
  { path: '**', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
