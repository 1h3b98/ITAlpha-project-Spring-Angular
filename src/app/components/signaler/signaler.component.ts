import { Component, Inject, OnInit } from '@angular/core';
import { Signaler } from 'src/app/models/signaler';
import { PostService } from 'src/app/services/postService/post-service.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-signaler',
  templateUrl: './signaler.component.html',
  styleUrls: ['./signaler.component.css']
})
export class SignalerComponent implements OnInit {
signalerList = ["nudity","violence","harassment","terrorisme","other"]
signaler : Signaler
type :any ;
description:any=""
  constructor(private postservice : PostService ,@Inject(MAT_DIALOG_DATA) public data : any,
  private dialogRef : MatDialogRef<SignalerComponent>
   ) { }
 
  ngOnInit(): void {
    this.signaler = new Signaler()
console.log("hhhhhhhhh"+this.data);
console.log(this.signaler.singalerType)
  }
  Category(value:any) {
    this.signaler.singalerType=value.value;
    return this.signaler;
}

affiche(){
  console.log(this.signaler)
  console.log("hhhhhhhhh"+this.data);
}
 
SignalerPost(){
  console.log()
   this.postservice.signalerPost(this.data,this.signaler).subscribe((Response)=>{ 
   });
}

}
