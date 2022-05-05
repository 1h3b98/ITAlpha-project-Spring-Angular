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
  this.name=this.Feed.username
  
}
else
this.name="Anonymous";
this.Feed.username=this.name
  }
  showfeed(){
    this.hidee=!this.hidee;
  }
  showdetails(){
    this.hide1=!this.hide1;
  }
 AnonymeCheck():Boolean{
   return this.name=="Anonymous"
 }
  

}
