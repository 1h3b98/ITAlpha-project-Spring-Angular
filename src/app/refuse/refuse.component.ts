import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Actions } from '../model/action';
import { Events } from '../model/events';
import { ActionService } from '../service/action.service';

@Component({
  selector: 'app-refuse',
  templateUrl: './refuse.component.html',
  styleUrls: ['./refuse.component.css']
})
export class RefuseComponent implements OnInit {
  actionI: Actions;
  aId: number;
  currentAction: Actions;

  constructor(private actionService:  ActionService,private httpClient: HttpClient) { }
  @Input() event:Events;
  ngOnInit(): void {
    this.getInviteAction(1,this.event.eventId)
  }
  getInviteAction(recieverId:number,eventId:number):number{
    this.actionService.getEventInvite(recieverId,eventId).subscribe((data)=>{this.actionI=data as Actions
    this.aId=this.actionI.actionId as number
    })
    return this.aId
  }

  refuseInvite(recieverId:number,eventId:number){
    this.currentAction= new Actions();
    this.currentAction.actionType="REPONSE";
    this.currentAction.accepted=false;
    
    this.aId=this.getInviteAction(recieverId,eventId);
    
  this.actionService.refuseInvite(this.currentAction,this.aId,recieverId,eventId).subscribe();
  }
}
