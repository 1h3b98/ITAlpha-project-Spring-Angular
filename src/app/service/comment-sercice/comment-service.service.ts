import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from 'src/app/env/env';
import { comment } from 'src/app/model/Comment';
import { Post } from 'src/app/model/Post';

@Injectable({
  providedIn: 'root'
})
export class CommentServiceService {

  commentUpdated: comment= new comment();;
  constructor(private http: HttpClient) { }
  getComment(id : number){
    return this.http.get<comment[]>(env.baseUrl+env.getCommentByPost+id);
      }

  addcomment(post:Post, comment :comment){
    return this.http.post(env.baseUrl+env.addCommentUrl+post.idPost,comment);
  }

  updateComment(content:string ,comment:comment){
return this.http.put(env.baseUrl+env.updateCommentUrl+comment.idComment,content);
  }
  deletecomment(idComment :number){
    return this.http.delete(env.baseUrl+env.deleteComentUrl+idComment);
  }

  likeComment(idcomment:number){
    return this.http.get(env.baseUrl+env.likeCommentUrl+idcomment);
  }

  addCommentToComment(idcomment:number,comment:comment){
    return this.http.post(env.baseUrl+env.addCommentToComment+idcomment,comment);
  }
}
