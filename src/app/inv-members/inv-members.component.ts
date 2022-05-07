import { Component, Input, OnInit } from '@angular/core';
import { Events } from '../model/events';
import { User } from '../model/user';
import { EventService } from '../service/event.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-inv-members',
  templateUrl: './inv-members.component.html',
  styleUrls: ['./inv-members.component.css']
})
export class InvMembersComponent implements OnInit {
  members: User[];
  users: User[];

  constructor(private eventService:  EventService,private userService:  UserService) { }
  @Input() event:Events;
  ngOnInit(): void {
    
  }

  getAllUser(){
    this.userService.getAllUser().subscribe((data:User[])=>this.users=data)
  }
  getMembers(eventId:number){
    this.eventService.getAllUsersInEvent(eventId).subscribe((data:User[])=>this.members=data);
  }
}
