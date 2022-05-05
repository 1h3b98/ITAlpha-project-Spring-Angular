import { Component, Input, OnInit } from '@angular/core';
import { userBadge } from 'src/app/models/userBadge';
import { FeedbackService } from 'src/app/services/feedback-service/feedback.service';

@Component({
  selector: 'app-tri-users',
  templateUrl: './tri-users.component.html',
  styleUrls: ['./tri-users.component.css']
})
export class TriUsersComponent implements OnInit {
listUB : userBadge[];
user:userBadge;
idConnected:number=1;
  constructor(private feedbackservice:FeedbackService) { }
index:number;
  ngOnInit(): void {
this.getUsersBadges();

        
  }
  checkuserCONNECTED(u:userBadge){
return u.userId==this.idConnected
  }
  getUsersBadges(){
    return this.feedbackservice.getUsersBadges().subscribe(
      Response=>{
        this.listUB=Response;
        console.log(Response)

      }
     );
  }


  gold(userBadge:userBadge):boolean{
    if(userBadge.badge=="GOLDEN")
    return true;
    return false;
  }
  bronze(userBadge:userBadge):boolean{
    if(userBadge.badge=="BRONZE")
    return true;
    return false;
  }
  silver(userBadge:userBadge):boolean{
    if(userBadge.badge=="SILVER")
    return true;
    return false;
  }

}
