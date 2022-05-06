import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Events } from '../model/events';
import { User } from '../model/user';
import { EventService } from '../service/event.service';

@Component({
  selector: 'app-members',
  templateUrl: './members.component.html',
  styleUrls: ['./members.component.css']
})
export class MembersComponent implements OnInit {
  members: User[];

  constructor(private eventService:  EventService,private httpClient: HttpClient) { }
  @Input() event:Events;
  ngOnInit(): void {
this.getMembers(this.event.eventId)
  }
getMembers(eventId:number){
  this.eventService.getAllUsersInEvent(eventId).subscribe((data:User[])=>this.members=data);
}
}
