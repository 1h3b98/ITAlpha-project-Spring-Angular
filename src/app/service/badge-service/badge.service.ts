import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { env } from 'src/app/env/env';
import { Badge } from 'src/app/model/Badge';
import { badgespoints } from 'src/app/model/badgespoints';
@Injectable({
  providedIn: 'root'
})
export class BadgeService {


  constructor(private http : HttpClient) { }

  getEvaluation(){
    return this.http.get<Badge>(env.baseUrl+env.getBadge+1);

  }
 
  getbadgepoints(id:number){
    return this.http.get<badgespoints>(env.baseUrl+env.getBadgePoints+"/"+id);
  }
}
