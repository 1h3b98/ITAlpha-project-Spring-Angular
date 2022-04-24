import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Events } from '../model/events';
import { env } from 'src/app/environments/env';

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
  updateEvent():Observable<object>{
    return this.http.put(env.baseUrl+env.UpdateEventUrl,  {responseType: 'text' as 'json'});
  }
  deleteEvent(id:any):Observable<object> {
    return this.http.delete(env.baseUrl+env.DeleteEventUrl+id);
  }
  getAllEvent():Observable<Events[]>{
    return this.http.get<Events[]>(env.baseUrl+env.GetAllEventUrl);
   }
}
