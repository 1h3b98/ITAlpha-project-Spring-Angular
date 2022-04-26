import { HttpClient } from '@angular/common/http';
import { Injectable, Input } from '@angular/core';

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
     
   return this.http.post(env.baseUrl+env.AddUrl+"/"+id+"/"+idd,f);
 }

 getfeedsrecieved(){
   return this.http.get<FeedBack[]>(env.baseUrl+env.feedsRecievedUrl+1);
 }
 getnbrRecieved(){
  return this.http.get<number>(env.baseUrl+env.getnbrRecieved+1);
}
getnbrsent(){
  return this.http.get<number>(env.baseUrl+env.getnbrSent+1);
}
getfeedssent(){
  return this.http.get<FeedBack[]>(env.baseUrl+env.getfeedsSent+1);
}

getUsers(){
  return this.http.get<number[]>(env.baseUrl+env.getusers);
}

getUsersnames(){
  return this.http.get<string[]>(env.baseUrl+env.getusersnames);
}

}