import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Events } from '../model/events';
import { env } from 'src/app/environments/env';
import { Actions } from '../model/action';




@Injectable({
  providedIn: 'root'
})
export class ActionService {
  constructor(private http: HttpClient) { }

  addAction(a:Actions,userId:number,eventId:number):Observable<Actions> {
    return this.http.post<Actions>(env.baseUrl+env.addActionUrl+userId+"/"+eventId,a);
   }
   acceptInvite(a:Actions,actionId:number,recieverId:number,eventId:number):Observable<Actions>{
    return this.http.put<Actions>(env.baseUrl+env.AcceptInviteActionUrl+actionId+"/"+recieverId+"/"+eventId, a);
  }
  
  refuseInvite(a:Actions,actionId:number,recieverId:number,eventId:number):Observable<Actions> {
    return this.http.delete<Actions>(env.baseUrl+env.RefuseInviteActionUrl+actionId+"/"+recieverId+"/"+eventId);
  }

  deleteLikeOrJoinOrFav(actionId:number,eventId:number,userId:number):Observable<Actions> {
    return this.http.delete<Actions>(env.baseUrl+env.DeleteOtherActionUrl+actionId+"/"+eventId+"/"+userId);
  }
  deleteOthers(actionId:number):Observable<Actions> {
    return this.http.delete<Actions>(env.baseUrl+env.DeleteOtherActionUrl+actionId);
  }
  getAllActions():Observable<Actions[]>{
    return this.http.get<Actions[]>(env.baseUrl+env.GetAllActionUrl);
   }

   getFavEvent(userId:number):Observable<Events[]>{
    return this.http.get<Events[]>(env.baseUrl+env.GetFavEventUrl+userId);
   }
   getOneActions(actionId:number):Observable<Actions>{
    return this.http.get<Actions>(env.baseUrl+env.GetOneActionUrl+actionId);
   }
   getAllInvites(recieverId:number):Observable<Events[]>{
    return this.http.get<Events[]>(env.baseUrl+env.GetInviteActionUrl+recieverId);
   }

   getEventInvite(recieverId:number,eventId:number):Observable<Actions>{
    return this.http.get<Actions>(env.baseUrl+env.GetInviteActionUrl+recieverId+"/"+eventId);
   }

   getLike(userId:number,eventId:number):Observable<Actions>{
    return this.http.get<Actions>(env.baseUrl+env.GetLikeActionUrl+userId+"/"+eventId);
   }
   getFav(userId:number,eventId:number):Observable<Actions>{
    return this.http.get<Actions>(env.baseUrl+env.GetFavActionUrl+userId+"/"+eventId);
   }
   
   getJoin(userId:number,eventId:number):Observable<Actions>{
    return this.http.get<Actions>(env.baseUrl+env.GetJoinActionUrl+userId+"/"+eventId);
   }
   getComments(eventId:number):Observable<Actions[]>{
    return this.http.get<Actions[]>(env.baseUrl+env.GetLikeActionUrl+eventId);
   }
}