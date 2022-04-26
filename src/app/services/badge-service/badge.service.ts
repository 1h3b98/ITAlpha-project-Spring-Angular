import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { env } from 'src/app/env/env';
import { Badge } from 'src/app/models/Badge';
@Injectable({
  providedIn: 'root'
})
export class BadgeService {

id:number;
  constructor(private http : HttpClient) { }

  getEvaluation(){
    return this.http.get<Badge>(env.baseUrl+env.getBadge+1);

  }
 
  
}
