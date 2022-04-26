import { Component, Inject, Input, OnInit } from '@angular/core';
import { Badge } from 'src/app/models/Badge';
import { Trophey } from 'src/app/models/Trophey';
import { BadgeService } from 'src/app/services/badge-service/badge.service';
import { CongratsService } from 'src/app/services/congrats-service/congrats.service';
import { TropheyService } from 'src/app/services/trophey-service/trophey.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Template } from '@angular/compiler/src/render3/r3_ast';

@Component({
  selector: 'app-congrats',
  templateUrl: './congrats.component.html',
  styleUrls: ['./congrats.component.css']
})
export class CongratsComponent implements OnInit {
  badge:Badge ;
  
   
  constructor(private badgeservice:BadgeService,private tropheyservice:TropheyService,private congratsservice: CongratsService,
    @Inject(MAT_DIALOG_DATA )public data :any ) { }

  ngOnInit(): void {
   this.badge=this.data;
  
  }


  checkdateBadge():boolean{
    return  this.congratsservice.checkDate(this.badge.date);
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
