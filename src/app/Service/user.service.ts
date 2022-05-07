
import { HttpClient, HttpHeaders,  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Offre } from '../Model/Offre';
import { User } from '../Model/User';
const httpOptions={
  headers:new HttpHeaders({'Content-Type':'application/json'})
};


@Injectable({
  providedIn: 'root'
})
export class UserService {
 user:User[];
 offre:Offre[];
 private url:'http://localhost:8081/pi-spring/api/getuser/id';
  constructor(private http: HttpClient){}
 
 // getAlluser() : Observable<string[]> {
  //  return this.http.get<string[]>(this.usersUrl);}
    addUser(user:User){
      return this.http.post<User>("http://localhost:8081/pi-spring/api/registration/add",user);
      }
      complétionprofil(employee:User) :Observable<Object> {
        return this.http.put<User>("http://localhost:8081/pi-spring/api/completerleprofil",employee);
     
      }
      getAlluser():Observable<User[]>{
  
        return this.http.get<User[]>("http://localhost:8081/pi-spring/api/getAlluser");    }

        getuser(id:number):Observable<User[]>{
        //  let jwt=this.token.getToken();
          //jwt="Bearer "+jwt;
          //let httpHeaders=new HttpHeaders({"Authorization":jwt})
          return this.http.get<User[]>("http://localhost:8081/pi-spring/api/getuser/id");
        }
     ListOffre():Observable<Offre[]>{
       return this.http.get<Offre[]>("http://localhost:8081/pi-spring/api/GetAllpub");
     }
     showProfile() {
      return this.http.get("http://localhost:8082/pi-spring/api/profil");
    }

  public deleteEmployee(employeeId: number): Observable<void> {
    return this.http.delete<void>("http://localhost:8081/pi-spring/api/delete/{id");
  }
}
