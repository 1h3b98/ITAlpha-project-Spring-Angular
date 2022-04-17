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

  constructor(private eventService:  EventService,private httpClient: HttpClient) {}

  ngOnInit(): void {
  this.eventService.getAllEvent().subscribe((data:Events[])=>this.eventList=data);
  }
  
  getoneEvent(e:Events){
    this.eventService.getEventById(e.eventId).subscribe(data=>{
      console.log(Response);
    });
  }
  addevent(e:Events){
    this.eventService.addEvent().subscribe(data=>{
      console.log(Response);
    });
}
updateevent(e:Events){
    this.eventService.updateEvent().subscribe(data=>{
      console.log(Response);
    });
}
  deletePost(e:Events){
      this.eventService.deleteEvent(e.eventId).subscribe(data=>{
        console.log(Response);
      });
  }
}