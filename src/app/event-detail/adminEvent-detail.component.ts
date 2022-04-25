import { HttpClient,HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Events } from '../model/events';
import { EventService } from '../service/event.service';

@Component({
  selector: 'admin-event-detail',
  templateUrl: './adminEvent-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class AdminEventDetailComponent implements OnInit {
  id :any;
  data:any
  oneEvent: Events ;
  param:any;
  constructor(
    private _routes:ActivatedRoute,
    private eventService:  EventService,
    private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.id=+this._routes.params.subscribe(params => {
     
      console.log(params)//log the entire params object
      this.id=params['Id']
      console.log('this.id ='+this.id)
     
      //console.log(params['event.eventId']) //log the value of id
    //this.id=+params['event.eventId'];
   
    this.getOne(this.id)
   });
  }
  getOne(id:any){
    console.log('id ='+this.id)
    this.eventService.getEventById(id).subscribe(data=>{
      this.oneEvent=data
      console.log(data)
    });
  }
  

    
    
  
}
