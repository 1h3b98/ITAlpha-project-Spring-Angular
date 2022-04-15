import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FeedBack } from 'src/app/models/FeedBack';
import { user } from 'src/app/models/user';
import { FeedbackService } from 'src/app/services/feedback-service/feedback.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
  feedback : FeedBack;
  id:number;
  ids:number;
  constructor(private feedbackservice:FeedbackService) { }

  ngOnInit(): void {
    this.feedback = new FeedBack();
    //this.listusers();
  }

save(){
  this.feedbackservice.addFeedback(this.feedback,this.id,this.ids);
}
/*
listusers() {
    this.feedbackservice.getusers.subscribe(
      (      data: any) => {
        console.log('users' + JSON.stringify(data));
        this.listuser = data;
      });}*/
}
