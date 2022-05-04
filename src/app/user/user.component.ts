import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Events } from '../model/events';
import { ActionService } from '../service/action.service';
import { HttpClient,HttpEventType } from '@angular/common/http';
import { User } from '../model/user';
import { Actions } from '../model/action';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  
  action: Actions;
  data:any;
  eventList : Events[] = [];
  user : User={userId : 1 , 
    firstname: "iheb",
	  lastname : "saad",
	  role: "admin"};

  constructor( private actionService:  ActionService,private httpClient: HttpClient) {}


  
  ngOnInit(): void {
    this.actionService.getFavEvent(this.user.userId).subscribe((data:Events[])=>this.eventList=data);
  }

 
  
  

}
