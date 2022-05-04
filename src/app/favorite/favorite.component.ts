import { HttpClient } from '@angular/common/http';
import { Component, OnInit,Input } from '@angular/core';
import { Actions } from '../model/action';
import { Events } from '../model/events';
import { ActionService } from '../service/action.service';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit {
  oneAction: Actions;
  test:boolean;
  data:any;
  EId: number;
  
  newAction: Actions;
  favoriteB:boolean;


  constructor(private actionService:  ActionService,private httpClient: HttpClient) { }
 @Input() event:Events;
  ngOnInit(): void {
    
    this.favStatus(1,this.event.eventId);
    
    
  }
  
  
  
  favStatus(uId:number,eId:number):Actions{
    this.actionService.getFav(uId,eId).subscribe(data=>{
      this.oneAction=data as Actions
      console.log(data)
     
      if (this.oneAction==null){
        this.favoriteB=false
      }else{
        this.favoriteB=true
      }
  });
  return this.data
}

  onClick(){
    if(this.favoriteB==true){
     this.deleteFav(1,this.event.eventId);
      this.favoriteB=false;
    }else{
      this.addFav(1,this.event.eventId);
      this.favoriteB=true;
    }

  }


  addFav(auId:number,aeId:number){
      this.newAction= new Actions();
      this.newAction.actionType="FAVORITE"
       console.log(this.newAction);
       
       this.actionService.addAction(this.newAction,auId,aeId).subscribe();
    }
  deleteFav(auId:number,aeId:number){
         
         this.actionService.deleteLikeOrJoinOrFav(this.oneAction.actionId,aeId,auId).subscribe(res=>{});
   }
}
