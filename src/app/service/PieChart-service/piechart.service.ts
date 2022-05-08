import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from 'src/app/env/env';
import { Event } from 'src/app/model/Event';

@Injectable({
  providedIn: 'root'
})
export class PiechartService {

  constructor(private http : HttpClient) { }

  getevents(){
    
    return this.http.get<Object[]>(env.baseUrl+env.getevents);
  }
  gettaux(){
    return this.http.get<number[]>(env.baseUrl+env.gettauxevent);
  }

}
