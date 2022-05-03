import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { comment } from 'src/app/models/Comment';
import { Post } from 'src/app/models/Post';
import { Signaler } from 'src/app/models/signaler';
import { CommentServiceService } from 'src/app/services/comment-sercice/comment-service.service';
import { PostService } from 'src/app/services/postService/post-service.service';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { SignalerComponent } from '../signaler/signaler.component';
import { DeleteDialogComponent } from 'src/app/components/delete-dialog/delete-dialog.component';
import { UpdateComponent } from '../update/update.component';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit  {

  @Input() post : Post;
  signaler : Signaler = new Signaler();
  @Output() DeletePost = new EventEmitter<Post>();
  @Output() updatePost = new EventEmitter(); 
  ListComment : comment[];
  valeurRadio : string;
  idPostSignler : number;
  retrievedImage:string ;
  UserImage:string ;
  nbrtotle: number;
  idPost:number;
  comment : comment;
  constructor(private postservice : PostService , private  CommentService: CommentServiceService ,public dialog: MatDialog) { }

  ngOnInit(): void {
    this.signaler = new Signaler();
    this.comment=new comment();
    this.retrievedImage='data:image/jpeg;base64,'+this.post.data
    this.UserImage='data:image/jpeg;base64,'+this.post.photo 
    this.nbrtotle= this.post.nbrLike+this.post.nbrdislike;
    this.idPost=this.post.idPost;
    console.log(this.post.photo)
  }

  openDialog() {
    this.dialog.open(SignalerComponent, {
    width:'30%'
    });
  }
  delete(u: Post){
    this.postservice.deletepost(u.idPost).subscribe((Response)=>{
     console.log(u.content+"++++++");
     this.updatePost.emit(u);
    
   });

 }
 getcomment(id : number){ 
  this.CommentService.getComment(id).subscribe(
    (data:comment[])=>{
      this.ListComment=data
      console.log(data)
      
    }

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
        this.updatePost.emit(post);
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
 if(post.etatLike==2 || post.etatLike==0){
  this.nbrtotle= this.post.nbrLike+this.post.nbrdislike;
  console.log(post.idPost)
this.postservice.likePost(post).subscribe((Response)=>{
  this.updatePost.emit(post);
});
 }else{
   this.removelike(post);
 }
}

dislikePost(post:Post){
if(post.etatLike==2 || post.etatLike==1){
  this.nbrtotle= this.post.nbrLike+this.post.nbrdislike;
  console.log(post.idPost)
this.postservice.dislikePost(post).subscribe((Response)=>{
  this.updatePost.emit(post);
}); 
 }else{
   this.removelike(post);
 }
}
removelike(post:Post){
  this.postservice.removeLike(this.post).subscribe(res=>{
    console.log("done")
    this.updatePost.emit(post);
  })
}
addcomment(post:Post){
  this.CommentService.addcomment(post,this.comment).subscribe((response) => {
      console.log(response);
      this.getcomment(post.idPost);
  });
  
    }


}
