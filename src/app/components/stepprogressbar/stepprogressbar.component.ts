import { Component, OnInit } from '@angular/core';
import { Badge } from 'src/app/models/Badge';
import { BadgeService } from 'src/app/services/badge-service/badge.service';

@Component({
  selector: 'app-stepprogressbar',
  templateUrl: './stepprogressbar.component.html',
  styleUrls: ['./stepprogressbar.component.css']
})
export class StepprogressbarComponent implements OnInit {
  badge:Badge ;

  constructor(private badgeservice:BadgeService) { }

  ngOnInit(): void {
    this.badge=new Badge();
  }
  gold():boolean{
    if(this.badge.badge=="GOLDEN")
    return true;
    return false;
  }
  bronze():boolean{
    if(this.badge.badge=="BRONZE")
    return true;
    return false;
  }
  silver():boolean{
    if(this.badge.badge=="SILVER")
    return true;
    return false;
  }
}
