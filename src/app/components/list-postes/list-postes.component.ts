import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { PostService } from 'src/app/services/postService/post-service.service';
import {MatDialog,MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { CreatePostComponent } from '../create-post/create-post.component';

@Component({
  selector: 'app-list-postes',
  templateUrl: './list-postes.component.html',
  styleUrls: ['./list-postes.component.css']
})
export class ListPostesComponent implements OnInit {
  Listposts : Post[];
 
  constructor(private postservice : PostService , private httpClient: HttpClient,public dialog: MatDialog) { }

  ngOnInit(): void {
   this.getPosts();
  }

  getPosts(){
    this.postservice.getPosts().subscribe(
      (data:Post[])=>{
        this.Listposts=data
        console.log(data)
      }
    );
  } 
  deletePost(u: Post){
    this.Listposts= this.Listposts.filter((e: Post)=>{
      return e.idPost!=u.idPost;   
  });}


  openDialog(){
    this.dialog.open(CreatePostComponent, {
      width:'50%'
      }).afterClosed().subscribe(val=>{
        if(val==='ok'){
          this.getPosts();
        }
      })
  }


}
