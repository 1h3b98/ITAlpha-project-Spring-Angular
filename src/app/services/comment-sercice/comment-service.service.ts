import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from 'src/app/env/env';
import { comment } from 'src/app/models/Comment';
import { Post } from 'src/app/models/Post';

@Injectable({
  providedIn: 'root'
})
export class CommentServiceService {

  constructor(private http: HttpClient) { }
  getComment(id : number){
    return this.http.get<comment[]>(env.baseUrl+env.getCommentByPost+id);
      }

  addcomment(post:Post, comment :comment){
    return this.http.post(env.baseUrl+env.addCommentUrl+post.idPost,comment);
  }
}
