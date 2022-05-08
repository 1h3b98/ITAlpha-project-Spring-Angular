import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Trophey } from 'src/app/model/Trophey';
import { env } from 'src/app/env/env';

@Injectable({
  providedIn: 'root'
})
export class TropheyService {
  constructor(private http : HttpClient) { }

  getTrophies(id:number){
    return this.http.get<Trophey[]>(env.baseUrl+env.getTrophies+id);

  }
 gettropheyToday(){
   return this.http.get<Trophey>(env.baseUrl+env.gettropheytoday+1);
 }

}
