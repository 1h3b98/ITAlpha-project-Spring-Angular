import { Component, Input, OnInit } from '@angular/core';
import { Offre } from '../Model/Offre';
import { UserService } from '../Service/user.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {
offre:Offre[];

  constructor(private us:UserService) { }

  ngOnInit(): void {
    this.us.ListOffre().subscribe(ofre=>{
      console.log(ofre);
      this.offre=ofre;
    })
  }

}
