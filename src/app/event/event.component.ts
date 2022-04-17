import { HttpClient,HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Events } from '../model/events';
import { EventService } from '../service/event.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

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
}
