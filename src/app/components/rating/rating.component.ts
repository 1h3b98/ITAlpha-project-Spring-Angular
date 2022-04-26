import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ForumService } from 'src/app/services/forum-service/forum.service';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styles: [
    `
    .star {
      position: relative;
      display: inline-block;
      font-size: 3rem;
      color: #FFD700;
    }
   
  `,
  ],
})
export class RatingComponent  {
  note:number
  ctrl = new FormControl(null, Validators.required);

constructor(private ForumService:ForumService,@Inject(MAT_DIALOG_DATA) public data  : any,
private dialogRef : MatDialogRef< RatingComponent>){}
  toggle() {
    if (this.ctrl.disabled) {
      this.ctrl.enable();
    } else {
      this.ctrl.disable();
    }
  }

  confirmeRating(){
    this.note=this.ctrl.value
    console.log(this.note+"note")
this.ForumService.rateForum(this.data,this.note).subscribe(res=>{
  console.log("done")
  this.dialogRef.close('update');
})
  } 
}
  

  


