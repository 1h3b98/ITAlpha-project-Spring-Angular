import { Component, Input, OnInit } from '@angular/core';
import { FeedBack } from 'src/app/models/FeedBack';
import { FeedbackService } from 'src/app/services/feedback-service/feedback.service';

@Component({
  selector: 'app-listfeeds',
  templateUrl: './listfeeds.component.html',
  styleUrls: ['./listfeeds.component.css']
})
export class ListfeedsComponent implements OnInit {
@Input() Feed:FeedBack;
hidee:boolean=true;
hide1:boolean=true;
name : string;

  constructor(private feedbackservice:FeedbackService) { }

  ngOnInit(): void {
if(this.Feed.status==true){
  this.name=this.Feed.fname
}
else
this.name="Anonymous"
console.log(this.name+"hhhhhhhh")
  }
  showfeed(){
    this.hidee=!this.hidee;
  }
  showdetails(){
    this.hide1=!this.hide1;
  }
  
}
