import { Component, Input, OnInit } from '@angular/core';
import { FeedBack } from 'src/app/models/FeedBack';
import { FeedbackService } from 'src/app/services/feedback-service/feedback.service';

@Component({
  selector: 'app-feedssent',
  templateUrl: './feedssent.component.html',
  styleUrls: ['./feedssent.component.css']
})
export class FeedssentComponent implements OnInit {
  @Input() Feedsent:FeedBack;
   hidee:boolean=true;
   hide1:boolean=true;

  constructor(private feedbackservice:FeedbackService) { }

  ngOnInit(): void {
  }

showfeed(){
  this.hidee=!this.hidee;
}
showdetails(){
  this.hide1=!this.hide1;
}
  
}
