import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { comment } from 'src/app/models/Comment';
import { Post } from 'src/app/models/Post';
import { Signaler } from 'src/app/models/signaler';
import { CommentServiceService } from 'src/app/services/comment-sercice/comment-service.service';
import { PostService } from 'src/app/services/postService/post-service.service';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { SignalerComponent } from '../signaler/signaler.component';
import { DeleteDialogComponent } from 'src/app/delete-dialog/delete-dialog.component';
import { UpdateComponent } from '../update/update.component';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input() post : Post;
  signaler : Signaler = new Signaler();
  @Output() DeletePost = new EventEmitter<Post>();
  @Output() updatePost = new EventEmitter(); 
  ListComment : comment[];
  valeurRadio : string;
  idPostSignler : number;
  retrievedImage:string ;
  nbrtotle: number;
  idPost:number;
  comment : comment;
  constructor(private postservice : PostService , private  CommentService: CommentServiceService ,public dialog: MatDialog) { }

  ngOnInit(): void {
    this.signaler = new Signaler();
    this.comment=new comment();
    this.retrievedImage='data:image/jpeg;base64,'+this.post.data
    this.nbrtotle= this.post.nbrLike+this.post.nbrdislike;
    this.idPost=this.post.idPost;
  }

  openDialog() {
    this.dialog.open(SignalerComponent, {
    width:'30%'
    });
  }
  delete(u: Post){
    this.postservice.deletepost(u.idPost).subscribe((Response)=>{
     console.log(u.content+"");
    this.DeletePost.emit(this.post);
   });

 }
 getcomment(id : number){ 
  this.CommentService.getComment(id).subscribe(
    (data:comment[])=>this.ListComment=data
  );
 }

getId(post : any){
  this.dialog.open(SignalerComponent,{
    width:'30%',
    data:post.idPost
  })
}
openConfirmationDialog(post : any){
  this.dialog.open(DeleteDialogComponent, {
    width:'30%',
    data:post.idPost
    }).afterClosed().subscribe(val=>{
      if(val==='ok'){
        this.DeletePost.emit(post);
      }
    })
}
openUpdateDialog(post : any){
  this.dialog.open(UpdateComponent, {
    width:'30%',
    data:post
    }).afterClosed().subscribe(val=>{
      if(val==='update'){
        this.updatePost.emit(post);
      }
    })
}

likePost(post:Post){
  this.nbrtotle= this.post.nbrLike+this.post.nbrdislike;
  console.log(post.idPost)
this.postservice.likePost(post).subscribe((Response)=>{
  
});
this.nbrtotle=this.nbrtotle+1;
}

dislikePost(post:Post){
  this.nbrtotle= this.post.nbrLike+this.post.nbrdislike;
  console.log(post.idPost)
this.postservice.dislikePost(post).subscribe((Response)=>{
  
});
this.nbrtotle=this.nbrtotle+1;
}
addcomment(post:Post){
  this.CommentService.addcomment(post,this.comment).subscribe((response) => {
      console.log(response);
      this.getcomment(post.idPost);
  });
  
    }


}
