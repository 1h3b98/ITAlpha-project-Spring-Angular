import { HttpClient,HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Events } from '../model/events';
import { EventService } from '../service/event.service';
import { ActionService } from '../service/action.service';
import { User } from '../model/user';
import { Actions } from '../model/action';


@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css'],
  
})
export class EventComponent implements OnInit {
  data:any;
  
  a:Actions;
  EId:number;
  oneAction: Actions ;
  newAction: Actions;
  bStatus:boolean;
  
  eventList : Events[] = [];
  user : User={userId : 1 , 
    firstname: "iheb",
	  lastname : "saad",
	  role: "admin"};
  users : User[] = [
    {userId : 1 , 
    firstname: "iheb",
	  lastname : "saad",
	  role: "admin"},
    
    {userId : 2 , 
    firstname: "bessem",
    lastname : "azizi",
    role: "user"},
    
    {userId : 3 , 
    firstname: "haifa",
    lastname : "berneg",
    role: "user"},
    
    {userId : 4 , 
    firstname: "amine",
    lastname : "boujouna",
    role: "user"},
    
    {userId : 5 , 
    firstname: "majd",
    lastname : "hmila",
    role: "user"},
  ];

  constructor(private eventService:  EventService,private actionService:  ActionService,private httpClient: HttpClient, private _router: Router) {}

  ngOnInit(): void {
  this.eventService.getAllEvent().subscribe((data:Events[])=>this.eventList=data);
  
  
  }
  favoriteB=true;
  onClick(event:Events) {
    this.EId = event.eventId;
    console.log(this.EId);
     this.a=this.favStatus(1,this.EId)
     console.log(this.a);
    if(this.a.favStatus==true){
      //this.addFav(1,this.EId);
      this.favoriteB=false;
    }
  
    }
    
  favStatus(uId:number,eId:number):Actions{
    this.actionService.getFav(uId,eId).subscribe(data=>{
      this.oneAction=data
      console.log(data)
  });
  return this.oneAction;
}
addFav(auId:number,aeId:number){
    
 this.newAction.actionType="FAVORITE"
  console.log(this.newAction);
  
  this.actionService.addAction(this.newAction,auId,aeId).subscribe();
}
deleteFav(auId:number,aeId:number){
    this.a=this.favStatus(auId,aeId);
    this.actionService.deleteLikeOrJoinOrFav(this.a.actionId,auId,aeId).subscribe();
}
  
  
  
}