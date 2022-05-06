import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Actions } from '../model/action';
import { Events } from '../model/events';
import { ActionService } from '../service/action.service';

@Component({
  selector: 'app-accept',
  templateUrl: './accept.component.html',
  styleUrls: ['./accept.component.css']
})
export class AcceptComponent implements OnInit {
  aId: number;
  actionI: any;
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

  acceptInvite(recieverId:number,eventId:number){
    this.currentAction= new Actions();
    this.currentAction.actionType="REPONSE";
    this.currentAction.accepted=true;
    this.aId=this.getInviteAction(recieverId,eventId)
    console.log(this.currentAction)
    console.log(this.aId)
    console.log(recieverId)
    console.log(eventId)
  this.actionService.acceptInvite(this.currentAction,this.aId,recieverId,eventId).subscribe();
  }
}
