import { Component, Input, OnInit } from '@angular/core';
import { Badge } from 'src/app/model/Badge';
import { badgespoints } from 'src/app/model/badgespoints';
import { BadgeService } from 'src/app/service/badge-service/badge.service';

@Component({
  selector: 'app-stepprogressbar',
  templateUrl: './stepprogressbar.component.html',
  styleUrls: ['./stepprogressbar.component.css']
})
export class StepprogressbarComponent implements OnInit {
  badge:Badge ;
  @Input() valeur:number;
  idUser:number=1;
badgepoints:badgespoints;
  constructor(private badgeservice:BadgeService) { }

  ngOnInit(): void {
    this.badge=new Badge();
   this.badgepoints=new badgespoints();
   this.badgeservice.getbadgepoints(this.idUser).subscribe(
    Response=>{
      this.badgepoints=Response;
      if(this.badgepoints.badge=="BRONZE" && this.badgepoints.user_percentage==0)
      this.valeur=this.badgepoints.user_percentage;
      else if (this.badgepoints.badge=="BRONZE" && this.badgepoints.user_percentage!=0)
      this.valeur=this.badgepoints.user_percentage;
      else if (this.badgepoints.badge=="SILVER" && this.badgepoints.user_percentage!=0)
      this.valeur=this.badgepoints.user_percentage+25;
      else if(this.badgepoints.user_percentage==0 && this.badgepoints.badge=="SILVER")
      this.valeur=this.badgepoints.user_percentage+55;
      else 
      this.valeur=100
console.log(this.valeur)
console.log(this.badgepoints.user_percentage)

    }
    
  );
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
  getbadgepoints(){
    return this.badgeservice.getbadgepoints(this.idUser).subscribe(
      Response=>{
        this.badgepoints=Response;
        if(this.badgepoints.badge=="bronze")
        this.valeur=this.badgepoints.user_percentage+(-3);
        else if (this.badgepoints.badge=="silver")
        this.valeur=this.badgepoints.user_percentage+40;
        else 
        this.valeur=82

      }
      
    );
    
  }
}

  // progressbar.js@1.0.0 version is used
// Docs: http://progressbarjs.readthedocs.org/en/1.0.0/

