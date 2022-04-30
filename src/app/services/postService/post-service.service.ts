import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from 'src/app/env/env';
import { Post } from 'src/app/models/Post';
import { Signaler } from 'src/app/models/signaler';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  post : Post;

  constructor(private http:HttpClient) { }

  
  getPosts(){
    return  this.http.get<Post[]>(env.baseUrl+env.getPosturl);
  }
  deletepost(p : number){
    return this.http.delete("http://localhost:8089/SpringMVC/post/delete/"+p);}

  update(idPost:number,p:string){
    console.log("+++++"+p)
    return this.http.put<any>("http://localhost:8089/SpringMVC/post/update/"+idPost,p);
  }
  signalerPost(p : number,signaler:Signaler){
    return this.http.post(env.baseUrl+env.signlerPostUrl+p,signaler);
  }
  getMemories(){
    return this.http.get<Post[]>(env.baseUrl+env.getMemoriesUrl);
}
likePost(post:Post){
  console.log(post.idPost)
  return this.http.get(env.baseUrl+env.likePostUrl+post.idPost);
}

dislikePost(post:Post){
  return this.http.get(env.baseUrl+env.dislikePostUrl+post.idPost);
}

removeLike(post:Post){
  return this.http.delete(env.baseUrl+env.removeLike+post.idPost);
}
addPostWithoutPhoto(post:Post){
  return this.http.post(env.baseUrl+env.addPostWithoutPhoto,post)
}


}
