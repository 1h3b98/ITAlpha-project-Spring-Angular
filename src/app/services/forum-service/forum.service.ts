import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from 'src/app/env/env';
import { Forum } from 'src/app/models/forum';

@Injectable({
  providedIn: 'root'
})
export class ForumService {

  constructor( private http:HttpClient) { }

  addArticle(forum:Forum){
  return this.http.post(env.baseUrl+env.addArticleUrl,forum);
  }
  deleteArticle(id :any){
   return this.http.delete(env.baseUrl+env.deleteArticleUrl+id);
  }
  updateArticle(idArticle:number,forum:Forum){
  this.http.put(env.baseUrl+env.updateArticleUrl+idArticle,forum);
  }
  getAllAricles(){
    return this.http.get<Forum[]>(env.baseUrl+env.getAllAriclesUrl);
  }

  getTags(){
    return this.http.get<string[]>(env.baseUrl+env.getTagsUrl);
  }

  addToFavoris(article: Forum){
   return this.http.get(env.baseUrl+env.addArticleTofavoritesUrl+article.idForum);
  }
  rateForum(idArticle:number,note:any){
    return this.http.get(env.baseUrl+env.rateArticleUrl+idArticle+"/"+note)
  }


}
