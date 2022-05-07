import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import { Router } from '@angular/router';
import { User } from '../Model/User';



@Injectable({
  providedIn: 'root'
})
export class AuthService {

 
  public loggedUser:string;
  public isloggedIn:Boolean=false;
  public roles:string[];
  token!:string;

  constructor(private http: HttpClient, private route:Router) {
  }
 login(user:User){
   return this.http.post<User>("http://localhost:8081/pi-spring/login",user,{observe:'response'});
 }
 saveToken(jwt:string){
   localStorage.setItem('jwt',jwt);
   this.token=jwt;
   this.isloggedIn=true;
 }
 loadToken(){
   this.token=localStorage.getItem('jwt');
 }
 getToken():string{
   return this.token;
 }
 signOut() {
  window.sessionStorage.clear();
}
 

  

 

  

  
}
