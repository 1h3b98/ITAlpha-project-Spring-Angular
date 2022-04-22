import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup ,Validator,FormBuilder, Validators} from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Post } from 'src/app/models/Post';
import { PostService } from 'src/app/services/postService/post-service.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  post :Post;
  actionBtn : string = "update"
  postF !:FormGroup;

  constructor(private formBuilder : FormBuilder, private postservice : PostService ,@Inject(MAT_DIALOG_DATA) public data : any,
  private dialogRef : MatDialogRef<UpdateComponent>
   ) { }

  ngOnInit(): void {
    this.post = new Post();
    this.postF= this.formBuilder.group({
      content :['',Validators.required]
    }) 
    if(this.data){
      this.postF.controls['content'].setValue(this.data.content);
    }

  }
update(){
  this.updatePost()
}

  updatePost(){
    this.postservice.update(this.data.idPost,this.postF.value).subscribe({
      next:(res)=>{
        this.postF.reset();
        this.dialogRef.close('update');
      }
    });
  }

}
