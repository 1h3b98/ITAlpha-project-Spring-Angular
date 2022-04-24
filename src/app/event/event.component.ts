import { HttpClient,HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Events } from '../model/events';
import { EventService } from '../service/event.service';


@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css'],
  
})
export class EventComponent implements OnInit {
  data:any;
  eventList : Events[] = [];

  constructor(private eventService:  EventService,private httpClient: HttpClient, private _router: Router) {}

  ngOnInit(): void {
  this.eventService.getAllEvent().subscribe((data:Events[])=>this.eventList=data);
  }
  
}