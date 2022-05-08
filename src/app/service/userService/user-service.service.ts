import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from 'src/app/env/env';

import { Post } from 'src/app/model/Post';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http :HttpClient) { }

  birthday(){
   return this.http.get<Post[]>(env.baseUrl+env.userbirthday)
  }
}
