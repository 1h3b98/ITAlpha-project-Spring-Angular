
import { Component, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConnectableObservable, from } from 'rxjs';


import { AppComponent } from 'src/app/app.component';
import { Badge } from 'src/app/model/Badge';
import { badgespoints } from 'src/app/model/badgespoints';
import { Trophey } from 'src/app/model/Trophey';
import { userBadge } from 'src/app/model/userBadge';
import { BadgeService } from 'src/app/service/badge-service/badge.service';
import { CongratsService } from 'src/app/service/congrats-service/congrats.service';
import { CongratsTService } from 'src/app/service/congratsT-service/congrats-t.service';
import { FeedbackService } from 'src/app/service/feedback-service/feedback.service';
import { TropheyService } from 'src/app/service/trophey-service/trophey.service';
import { CongratsTComponent } from '../congrats-t/congrats-t.component';
import { CongratsComponent } from '../congrats/congrats.component';
declare const confetti:any;
@Component({
  selector: 'app-badge',
  templateUrl: './badge.component.html',
  styleUrls: ['./badge.component.css']
})

export class BadgeComponent implements OnInit {
  
  listUB : userBadge[];
  user:userBadge;
  idConnected:number=1;
  index:number;


  badge:Badge ;
  @Output() nbrrecieved:number;
  Listtrophey: Trophey[];
  trophey:Trophey;
  @Input() ttrophey : Trophey;
  idUser:number;
  badgepoints:badgespoints;
  valeur:number;
  constructor(private BadgeService:BadgeService,private tropheyservice:TropheyService ,private congratsservice: CongratsService,public dialog: MatDialog,private congratstservice : CongratsTService,
    private feedbackservice:FeedbackService) { }

  ngOnInit(): void {
    this.badgepoints=new badgespoints();
    this.user=new userBadge();
    this.getUsersBadges();

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

    this.BadgeService.getbadgepoints(this.idUser).subscribe(
      Response=>{
        this.badgepoints=Response;
        if(this.badgepoints.badge=="BRONZE")
        this.valeur=this.badgepoints.user_percentage;
        else if (this.badgepoints.badge=="SILVER")
        this.valeur=this.badgepoints.user_percentage+40;
        else 
        this.valeur=100
  console.log(this.valeur)
  console.log(this.badgepoints.user_percentage)
  
      }
      
    );
    this.tropheyservice.getTrophies(1).subscribe(
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

  checkuserCONNECTED(u:userBadge){
    return u.userId==this.idConnected
      }
      getUsersBadges(){
        return this.feedbackservice.getUsersBadges().subscribe(
          Response=>{
            this.listUB=Response;
            console.log(Response)
    
          }
         );
      }
    
    
      gold1(userBadge:userBadge):boolean{
        if(userBadge.badge=="GOLDEN")
        return true;
        return false;
      }
      bronze1(userBadge:userBadge):boolean{
        if(userBadge.badge=="BRONZE")
        return true;
        return false;
      }
      silver1(userBadge:userBadge):boolean{
        if(userBadge.badge=="SILVER")
        return true;
        return false;
      }
    
  modelStyle: any = {
    display: 'none'
  };

  modalShow = false;
  zoneIsClicked: any;

  addClickEvent(e:any) {
    let x = e.currentTarget.getBoundingClientRect();
    if (e.type === 'click') {
      this.modalShow = true;
    }
    else if (e.type === 'mouseenter') {
      this.modelStyle = {
        top: 0 + 'px',
        left: 0 + 'px',

        height: screen.availHeight + 'px',
        width: screen.availWidth + 'px',
        display: 'flex'
      };
    }
    else if (e.type === 'mouseleave' && e.clientX < x.right) {
      this.modelStyle = {
        display: 'none'
      };
    }
  }

  onPopEvent() {
    this.modelStyle = {
      display: 'none'
    };
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
    this.tropheyservice.getTrophies(id).subscribe(
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
