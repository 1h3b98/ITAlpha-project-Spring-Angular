import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { comment } from 'src/app/models/Comment';
import { Post } from 'src/app/models/Post';
import { CommentServiceService } from 'src/app/services/comment-sercice/comment-service.service';
import { PostService } from 'src/app/services/postService/post-service.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input() post : Post;
  @Output() DeletePost = new EventEmitter<Post>();
  ListComment : comment[]
  constructor(private postservice : PostService , private  CommentService: CommentServiceService) { }

  ngOnInit(): void {
  }

  delete(u: Post){
    this.postservice.deletepost(u.idPost).subscribe((Response)=>{
     console.log(u.content+"");
    this.DeletePost.emit(this.post);
   });

 }
 void(id : number){
  this.CommentService.getComment(id).subscribe(
    (data:comment[])=>this.ListComment=data
  );
 }

}
