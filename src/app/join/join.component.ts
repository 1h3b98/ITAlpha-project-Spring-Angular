import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Actions } from '../model/action';
import { Events } from '../model/events';
import { ActionService } from '../service/action.service';

@Component({
  selector: 'app-join',
  templateUrl: './join.component.html',
  styleUrls: ['./join.component.css']
})
export class JoinComponent implements OnInit {
  JoinB: boolean;
  oneAction: Actions;
  data: Actions;
  newAction: Actions;
  

  constructor(private actionService:  ActionService,private httpClient: HttpClient) { }
  @Input() event:Events;
  ngOnInit(): void {
    this.JoinStatus(1,this.event.eventId);
  }
  JoinStatus(uId:number,eId:number):Actions{
    this.actionService.getJoin(uId,eId).subscribe(data=>{
      this.oneAction=data as Actions
      console.log(data)
     
      if (this.oneAction==null){
        this.JoinB=false
      }else{
        this.JoinB=true
      }
  });
  return this.data
}

  onClick(){
    if(this.JoinB==true){
     this.deleteJoin(1,this.event.eventId);
      this.JoinB=false;
    }else{
      this.addJoin(1,this.event.eventId);
      this.JoinB=true;
    }

  }


  addJoin(auId:number,aeId:number){
      this.newAction= new Actions();
      this.newAction.actionType="JOINA"
       console.log(this.newAction);
       
       this.actionService.addAction(this.newAction,auId,aeId).subscribe();
    }
  deleteJoin(auId:number,aeId:number){
         
         this.actionService.deleteLikeOrJoinOrFav(this.oneAction.actionId,aeId,auId).subscribe(res=>{});
   }
}
