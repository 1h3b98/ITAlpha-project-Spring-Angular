
import { Component, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConnectableObservable, from } from 'rxjs';

import { AppComponent } from 'src/app/app.component';
import { Badge } from 'src/app/models/Badge';
import { Trophey } from 'src/app/models/Trophey';
import { BadgeService } from 'src/app/services/badge-service/badge.service';
import { CongratsService } from 'src/app/services/congrats-service/congrats.service';
import { CongratsTService } from 'src/app/services/congratsT-service/congrats-t.service';
import { TropheyService } from 'src/app/services/trophey-service/trophey.service';
import { CongratsTComponent } from '../congrats-t/congrats-t.component';
import { CongratsComponent } from '../congrats/congrats.component';
declare const confetti:any;
@Component({
  selector: 'app-badge',
  templateUrl: './badge.component.html',
  styleUrls: ['./badge.component.css']
})

export class BadgeComponent implements OnInit {
  
 badge:Badge ;
 @Output() nbrrecieved:number;
  Listtrophey: Trophey[];
  trophey:Trophey;
  @Input() ttrophey : Trophey;

  constructor(private BadgeService:BadgeService,private tropheyservice:TropheyService ,private congratsservice: CongratsService,public dialog: MatDialog,private congratstservice : CongratsTService) { }

  ngOnInit(): void {
    this.badge=new Badge();
    console.log(this.nbrrecieved);
    this.BadgeService.getEvaluation().subscribe(
      Response=>{
        this.badge=Response;
        if(this.checkdateBadge()){
        this. openDialog(this.badge);
       this.start();
       this.stop();

        }
      }
    );
    this.tropheyservice.getTrophies().subscribe(
      (data1:Trophey[])=>this.Listtrophey=data1
    );
    

    this.trophey=new Trophey();
    this.congratstservice.gettropheyToday().subscribe(
      Response=>{
        this.trophey=Response;
        console.log(this.trophey.id);
        if(this.checkTrophey()){
        this.openDialog1(this.trophey);
        this.start();
        this.stop();}
      }
    );
    
    
    
    
  }

  start = () => {
    setTimeout(function() {
        confetti.start()
    }, 1000); // 1000 is time that after 1 second start the confetti ( 1000 = 1 sec)
};
 stop = () => {
  setTimeout(function() {
      confetti.stop()
  }, 6000); // 5000 is time that after 5 second stop the confetti ( 5000 = 5 sec)
};
  openDialog(badge:any) {
    this.dialog.open(CongratsComponent, {
    width:'30%',
    data:badge

    });
  }

  openDialog1(trophey:any) {
    this.dialog.open(CongratsTComponent, {
    width:'30%',
    data :trophey
    });
    console.log(this.trophey);
  }





  void(){
    return this.BadgeService.getEvaluation().subscribe(
      (data:Badge)=>this.badge=data
    );
    
   }

   void1(id : number){
    this.tropheyservice.getTrophies().subscribe(
      (data1:Trophey[])=>this.Listtrophey=data1
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
  test(){
    console.log("bb");
    
  }
    
  checkdateBadge():boolean{
    return  this.congratsservice.checkDate(this.badge.date);
  }
 
  checkTrophey():boolean{
    console.log(true)
    return this.trophey!=null;
  }


  getTrophey(){
    this.congratstservice.gettropheyToday().subscribe(
      Response=>{
        this.trophey=Response;
      }
    );
  }
 
}
