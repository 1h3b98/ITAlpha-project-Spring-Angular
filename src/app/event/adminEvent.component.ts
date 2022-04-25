import { HttpClient,HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Events } from '../model/events';
import { EventService } from '../service/event.service';

@Component({
  selector: 'admin-event',
  templateUrl: './adminEvent.component.html',
  styleUrls: ['./event.component.css']
})
export class AdminEventComponent implements OnInit {

  eventList : Events[] = [];
  newE:Events;
  test: Events;
  Eid :number;

  constructor(private eventService:  EventService,private httpClient: HttpClient) {}

  ngOnInit(): void {
    this.newE=new Events();
    
  this.eventService.getAllEvent().subscribe((data:Events[])=>this.eventList=data);
  
  }
  onClick(event:Events) {
    this.Eid = event.eventId;
    console.log(this.Eid);
  }
  getoneEvent(e:Events){
    
    this.eventService.getEventById(e.eventId).subscribe(data=>{
      console.log(Response);
    });
  }
  addevent(){
    
    this.newE.startTime=this.newE.startTime+" 00:00:00";
    this.newE.endTime=this.newE.endTime+" 00:00:00";
    console.log(this.newE);
    
    this.eventService.addEvent(this.newE).subscribe(()=>{
      
      this.eventList.push(this.newE)
      
      console.log(this.eventList);

    });
}
updateevent(){
    this.newE.eventId=this.Eid;
    this.newE.startTime=this.newE.startTime+" 00:00:00";
    this.newE.endTime=this.newE.endTime+" 00:00:00";
    console.log(this.newE);
    this.eventService.updateEvent(this.newE).subscribe(()=>{
      this.ngOnInit();
      console.log(this.eventList);
    });
}
  deleteEvent(e:Events){
    this.newE = e
      this.eventService.deleteEvent(e).subscribe(()=>this.ngOnInit());
  }
}