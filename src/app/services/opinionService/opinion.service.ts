import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from 'src/app/env/env';

import { opinion } from 'src/app/models/opinion';

@Injectable({
  providedIn: 'root'
})
export class OpinionService {


  constructor(private http :HttpClient) { }


  getOpinionByArticle(idArticle : number){
return this.http.get<opinion[]>(env.baseUrl+env.getOpinionByArticle+idArticle)
  }
  addOpinion(opinion :opinion , idArticle:number){
return this.http.post(env.baseUrl+env.addOpinion+idArticle,opinion);
  }

  deleteOpinion(idOpinion:number){
return this.http.delete(env.baseUrl+env.deleteOpinion+idOpinion);
  }
  updateopinion(idOpinion:number ,opinion :opinion){
return this.http.put(env.baseUrl+env.updateopinion+idOpinion,opinion);
  }
}
