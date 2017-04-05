import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MyUserComponent } from "app/components/my-user-component/my-user-component.component";
import { MyKweetComponent } from "app/components/my-kweet-component/my-kweet-component.component";
import { MyLocationComponent } from "app/components/my-location-component/my-location-component.component";
import { MyRoleComponent } from "app/components/my-role-component/my-role-component.component";
import { MyLogInComponent } from "app/components/my-login-component/my-login-component.component";
import { MyProfileComponent } from "app/components/my-profile-component/my-profile-component.component";
import { AuthGuard } from "app/guards/authentication.guard";
import { MyLogOutComponent } from "app/components/my-logout-component/my-logout-component.component";
import { OtherProfileComponent } from "app/components/other-profile-component/other-profile-component.component";
import { UpdateProfileComponent } from "app/components/update-profile-component/update-profile-component.component";
import { MyStartComponent } from "app/components/my-start-component/my-start-component.component";

const routes: Routes = [
  { path: 'user',  component: MyUserComponent },
  { path: 'kweet',  component: MyKweetComponent },
  { path: 'location',  component: MyLocationComponent },
  { path: 'role',  component: MyRoleComponent },
  { path: 'otherprofile',  component: OtherProfileComponent },
  { path: 'profile', component: MyProfileComponent, canActivate: [AuthGuard] },
  { path: 'login',  component: MyLogInComponent },
  { path: 'logout', component: MyLogOutComponent },
  { path: 'updateprofile', component: UpdateProfileComponent, canActivate: [AuthGuard] },
  { path: 'start', component: MyStartComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class AppRoutingModule {}
