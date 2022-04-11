import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Post } from 'src/app/Model/Post';
import { PostService } from 'src/app/Service/PostService/post.service';


@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

 @Input() post : Post;
 @Output() DeletePost = new EventEmitter<Post>();
  constructor(private postservice : PostService) { }

  ngOnInit(): void {
  }

  delete(u: Post){
     this.postservice.deletepost(u.idPost).subscribe((Response)=>{
      console.log(Response);
     this.DeletePost.emit(this.post);
    });
  }

}
