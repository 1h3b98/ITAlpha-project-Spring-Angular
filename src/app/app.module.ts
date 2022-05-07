import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninComponent } from './signin/signin.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

import { EventComponent } from './offre/event.component';
import { ProfilComponent } from './profil/profil.component';
import { AddEventComponent } from './add-event/add-event.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { Routes } from '@angular/router';
const routes: Routes = [
  //{path:'signin',component:SigninComponent},
  {path:'',redirectTo:'login' ,pathMatch:'full'},

  {path:'login',component:LoginComponent},
  {path:'profil',component:ProfilComponent},
  {path:'dashbord',component:DashboardComponent},
 {path:'event',component:EventComponent},

];

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    LoginComponent,
    HomeComponent,
    EventComponent,
    ProfilComponent,
    AddEventComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
    
  ],
  providers: [ ],
  bootstrap: [AppComponent]
})
export class AppModule { }
