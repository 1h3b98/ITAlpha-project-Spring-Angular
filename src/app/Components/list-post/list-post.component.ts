import { HttpClient,HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/Model/Post';
import { PostService } from 'src/app/Service/PostService/post.service';

@Component({
  selector: 'app-list-post',
  templateUrl: './list-post.component.html',
  styleUrls: ['./list-post.component.css']
})
export class ListPostComponent implements OnInit {
  Listposts : Post[];
  post: Post;
  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  
  constructor(private  postservice: PostService, private httpClient: HttpClient) { }


  ngOnInit(): void {
    this.postservice.getPosts().subscribe(
      (data:Post[])=>this.Listposts=data
    );
 
  }


  deletePost(u: Post){
    this.Listposts= this.Listposts.filter((e: Post)=>{
      return e.idPost!=u.idPost;
  });

}

}
