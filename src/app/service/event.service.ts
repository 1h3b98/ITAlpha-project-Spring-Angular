import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Events } from '../model/events';
import { env } from 'src/app/environments/env';
import { user } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  constructor(private http: HttpClient) { }

  addEvent (e:Events):Observable<Events> {
   return this.http.post<Events>(env.baseUrl+env.AddEventUrl, e);
  }
  getEventById(id:any):Observable<Events>{
   return this.http.get<Events>(env.baseUrl+env.GetOneEventUrl+id);
  }
  updateEvent(e:Events):Observable<Events>{
    return this.http.put<Events>(env.baseUrl+env.UpdateEventUrl, e);
  }
  deleteEvent(e:Events):Observable<Events> {
    return this.http.delete<Events>(env.baseUrl+env.DeleteEventUrl+e.eventId);
  }
  getAllEvent():Observable<Events[]>{
    return this.http.get<Events[]>(env.baseUrl+env.GetAllEventUrl);
   }

   getFavEvent(u:user):Observable<Events[]>{
    return this.http.get<Events[]>(env.baseUrl+env.GetFavEventUrl+u.id);
   }
}
