import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddEventComponent } from './add-event/add-event.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EventComponent } from './offre/event.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfilComponent } from './profil/profil.component';

const routes: Routes = [
  //{path:'signin',component:SigninComponent},
  {path:'',redirectTo:'login' ,pathMatch:'full'},

  {path:'login',component:LoginComponent},
  {path:'home',component:HomeComponent},
  {path:'profil',component:ProfilComponent},
  {path:'event',component:EventComponent},
  {path:'addevent',component:AddEventComponent},
  {path:'dashbord',component:DashboardComponent},
  
 
    
 /*   {
      path: "login",
      component: LoginComponent,
      //canActivate: [CanLoginGuard]
    },
    { path: "profil", component: ProfilComponent },
    { path: "", redirectTo: "login", pathMatch: "full" },
    {path:"dashbord",component:DashboardComponent},
    */

  

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
