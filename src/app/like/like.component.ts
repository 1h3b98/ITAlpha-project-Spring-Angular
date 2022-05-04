import { HttpClient } from '@angular/common/http';
import { Component, OnInit ,Input} from '@angular/core';
import { Actions } from '../model/action';
import { Events } from '../model/events';
import { ActionService } from '../service/action.service';

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.css']
})
export class LikeComponent implements OnInit {
  likeB:boolean;
  oneAction: Actions;
  data: Actions;
  newAction: Actions;

  constructor(private actionService:  ActionService,private httpClient: HttpClient) { }
  @Input() event:Events;
  ngOnInit(): void {
    
    this.LikeStatus(1,this.event.eventId);
  }
  LikeStatus(uId:number,eId:number):Actions{
    this.actionService.getLike(uId,eId).subscribe(data=>{
      this.oneAction=data as Actions
      console.log(data)
     
      if (this.oneAction==null){
        this.likeB=false
      }else{
        this.likeB=true
      }
  });
  return this.data
}

  onClick(){
    if(this.likeB==true){
     this.deleteLike(1,this.event.eventId);
      this.likeB=false;
    }else{
      this.addLike(1,this.event.eventId);
      this.likeB=true;
    }

  }


  addLike(auId:number,aeId:number){
      this.newAction= new Actions();
      this.newAction.actionType="LIKEA"
       console.log(this.newAction);
       
       this.actionService.addAction(this.newAction,auId,aeId).subscribe();
    }
  deleteLike(auId:number,aeId:number){
         
         this.actionService.deleteLikeOrJoinOrFav(this.oneAction.actionId,aeId,auId).subscribe(res=>{});
   }
}
