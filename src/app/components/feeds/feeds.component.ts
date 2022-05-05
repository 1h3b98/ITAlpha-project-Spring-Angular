import { Component, Input, OnInit, Output } from '@angular/core';
import { FeedBack } from 'src/app/models/FeedBack';
import { FeedbackService } from 'src/app/services/feedback-service/feedback.service';

@Component({
  selector: 'app-feeds',
  templateUrl: './feeds.component.html',
  styleUrls: ['./feeds.component.css']
})
export class FeedsComponent implements OnInit {

  constructor(private feedbackservice:FeedbackService) { }
  Listf: FeedBack[];
  Listfs: FeedBack[];
  hide:boolean=false;
  hide1:boolean=true;
  nb:number=0;
  nbs:number=0;

  id:number=0;
  ids:number=0;
  feedback : FeedBack;
  filteredString : string='';
  filteredString1 : string='';

  ngOnInit(): void {
    this.feedback = new FeedBack();
this.filteredString1=this.filteredString;
    this.getfeedsrecieved();
    this.getnbrrecieved();
    this.getfeedsent();
    this.getnbrsent();
  }
  save(){
    this.feedbackservice.addFeedback(this.feedback,2,1).subscribe(res=>{
      this.getnbrrecieved();
      this.getfeedsrecieved();
      this.getfeedsent();
      this.getnbrsent();
    });
    
  }
  
  getnbrrecieved(){
    return this.feedbackservice.getnbrRecieved().subscribe(
      Response=>{
        this.nb=Response;
      }
     );
  }
  getnbrsent(){
    return this.feedbackservice.getnbrsent().subscribe(
      Response=>{
        this.nbs=Response;
      }
     );
  }
  
  getfeedsrecieved(){
    return this.feedbackservice.getfeedsrecieved().subscribe(
      Response=>{
        this.Listf=Response;
        
      }
    );
    
  }
  
getfeedsent(){
    return this.feedbackservice.getfeedssent().subscribe(
      Response=>{
        this.Listfs=Response;

        
      }
    );
    
  }
  feedsSend(){
    this.hide=true;

  }
  feedsRecieved(){
    this.hide=false;

  }
}
