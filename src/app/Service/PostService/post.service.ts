import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from 'src/app/environments/env';
import { Post } from 'src/app/Model/Post';

@Injectable({
  providedIn: 'root'
})
export class PostService {


  constructor(private http:HttpClient  ) { }

  getPosts(){
    return this.http.get<Post[]>(env.baseUrl+env.GetPostUrl); }

    deletepost(p : number){
      return this.http.delete("http://localhost:8082/SpringMVC/post/delete?id="+p);}
     

}
