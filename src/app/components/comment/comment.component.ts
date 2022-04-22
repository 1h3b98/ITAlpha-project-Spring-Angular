import { Component, Input, OnInit, Output  ,EventEmitter} from '@angular/core';
import { comment } from 'src/app/models/Comment';
import { CommentServiceService } from 'src/app/services/comment-sercice/comment-service.service';
import { FormGroup ,Validator,FormBuilder, Validators} from '@angular/forms';


@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  ListComment : comment[];
  @Input() comment : comment;
  commentForum !:FormGroup;
  showUpdate :boolean=true;
  commentUpdated:comment;
  @Output() updatecomment = new EventEmitter(); 
  constructor(private formBuilder : FormBuilder, private  CommentService: CommentServiceService) {  
    
  }
  ngOnInit(): void {
    this.commentUpdated=new comment();
    this.commentForum= this.formBuilder.group({
      content :['',Validators.required]
    }) 
  }

  get(){
    this.showUpdate=false
    this.commentForum.controls['content'].setValue(this.comment.content);
  }

  updateComment(comment:comment){
    console.log(comment.idComment);
    this.CommentService.updateComment(this.commentForum.value,comment).subscribe(res=>{
      this.showUpdate=true
      this.updatecomment.emit(comment);
    });
   
  }

  deleteComment(comment:comment){
    this.CommentService.deletecomment(comment.idComment).subscribe(res=>{
      console.log("done !")
      this.updatecomment.emit(comment);
    })
  }

  likeComment(idcomment:number){
    this.CommentService.likeComment(idcomment).subscribe(res=>{
      console.log(res)
    })
  }

} 