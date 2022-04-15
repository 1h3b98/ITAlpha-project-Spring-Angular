import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { FeedBack } from 'src/app/models/FeedBack';
import { user } from 'src/app/models/user';
import { env } from 'src/app/env/env';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private http : HttpClient) { }

  
 addFeedback(f: FeedBack,id:number,idd:number)
 {
     
   return this.http.post(env.baseUrl+env.AddUrl+"/"+id+"/"+idd,f).subscribe();
 }

 getusers():Observable<user[]>{
   return this.http.get<user[]>(env.baseUrl+env.getusersurl);
 }


}