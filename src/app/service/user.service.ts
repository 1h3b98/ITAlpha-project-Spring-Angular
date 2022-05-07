import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { env } from "../environments/env";
import { User } from "../model/user";

@Injectable({
    providedIn: 'root'
  })
  export class UserService {
    constructor(private http: HttpClient) { }

    getAllUser():Observable<User[]>{
        return this.http.get<User[]>(env.baseUrl+env.GetAllUserUrl);
       }
  }