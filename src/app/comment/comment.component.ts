import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Actions } from '../model/action';
import { Events } from '../model/events';
import { ActionService } from '../service/action.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  comments: Actions[];

  constructor(private actionService:  ActionService,private httpClient: HttpClient) { }
  @Input() event:Events;
  ngOnInit(): void {
    this.actionService.getComments(this.event.eventId).subscribe((data:Actions[])=>{this.comments=data
    console.log(data)});
  }
  deleteComment(aId:number){
      this.actionService.deleteOthers(aId).subscribe(()=>this.ngOnInit());
  }
}
