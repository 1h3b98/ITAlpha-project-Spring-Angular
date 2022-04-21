import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Trophey } from 'src/app/models/Trophey';
import { env } from 'src/app/env/env';

@Injectable({
  providedIn: 'root'
})
export class TropheyService {
  constructor(private http : HttpClient) { }

  getTrophies(){
    return this.http.get<Trophey[]>(env.baseUrl+env.getTrophies+1);

  }
 gettropheyToday(){
   return this.http.get<Trophey>(env.baseUrl+env.gettropheytoday+1);
 }

}
