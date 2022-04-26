import { ThisReceiver } from '@angular/compiler';
import { Component, Inject, Input, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FeedBack } from 'src/app/models/FeedBack';
import { user } from 'src/app/models/user';
import { FeedbackService } from 'src/app/services/feedback-service/feedback.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
  user:user;
  feedback : FeedBack;
   nb:number=0;
  id:number=1;
  Listf: FeedBack[];
ListU:user[];
  ids:number=2;
  
  constructor(private feedbackservice:FeedbackService ) { }

  ngOnInit(): void {
    this.feedback = new FeedBack();
    //this.listusers();

    this.listusers();
  }



listusers() {
    this.feedbackservice.getUsers().subscribe(
      (      data: any) => {
        console.log('users' + JSON.stringify(data));
        this.ListU = data;
      });}
}
