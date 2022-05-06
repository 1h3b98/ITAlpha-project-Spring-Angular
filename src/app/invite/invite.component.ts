import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Actions } from '../model/action';
import { Events } from '../model/events';
import { ActionService } from '../service/action.service';

@Component({
  selector: 'app-invite',
  templateUrl: './invite.component.html',
  styleUrls: ['./invite.component.css']
})
export class InviteComponent implements OnInit {
  eventList: Events[] = [];
  
  currentAction: Actions;
  actionI:Actions;
  data: Actions;
  aId:number;

  constructor(private actionService:  ActionService,private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.getInviteEvent(1)
  }
getInviteEvent(recieverId:number){
  this.actionService.getAllInvites(recieverId).subscribe((data:Events[])=>this.eventList=data);
}





}

