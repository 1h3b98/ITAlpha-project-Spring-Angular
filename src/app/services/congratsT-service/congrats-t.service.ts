import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from 'src/app/env/env';
import { Trophey } from 'src/app/models/Trophey';

@Injectable({
  providedIn: 'root'
})
export class CongratsTService {

  constructor(private http : HttpClient) { }
  gettropheyToday(){
    return this.http.get<Trophey>(env.baseUrl+env.gettropheytoday+1);
  }
 
}
