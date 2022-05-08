import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DeleteDialogComponent } from 'src/app/components/delete-dialog/delete-dialog.component';
import { env } from 'src/app/env/env';
import { Forum } from 'src/app/model/forum';
import { opinion } from 'src/app/model/opinion';
import { ForumService } from 'src/app/service/forum-service/forum.service';
import { OpinionService } from 'src/app/service/opinionService/opinion.service';
import { AddForumComponent } from '../add-forum/add-forum.component';
import { DeleteforumDialagComponent } from '../deleteforum-dialag/deleteforum-dialag.component';


import { RatingComponent } from '../rating/rating.component';

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent implements OnInit {
  rate:any;
  tags :any  
  UserImage:string ; 
  ListTagsByOne:string[] 
  @Input() article :Forum
  @Output() DeleteArticle = new EventEmitter<Forum>();
  @Output() updateArticle = new EventEmitter(); 

  constructor(public dialog: MatDialog ,private OpinionService:OpinionService, private http:HttpClient,private ForumService:ForumService) { }


  ngOnInit(): void {
    this.rate=this.article.rating
    this.getTagsbyArticle(this.article)
    this.UserImage='data:image/jpeg;base64,'+this.article.photo  

   
  }
 
  openConfirmationDialog(article : any){
    this.dialog.open(DeleteforumDialagComponent, {
      width:'30%',
      data:article.idForum
      }).afterClosed().subscribe(val=>{
        if(val==='ok'){
          this.DeleteArticle.emit(article);
        }
      })
  } 

  getTagsbyArticle(forum:Forum){
    this.ForumService.getTagsbyArticle(forum).subscribe(res=>{
      this.ListTagsByOne=res;
    })
  }
 
    addToFavoris(article: Forum){
      this.ForumService.addToFavoris(article).subscribe()
    }

    rating(){
      this.dialog.open(RatingComponent, {
        width:'30%',
        data:this.article.idForum
        }).afterClosed().subscribe(val=>{
          if(val==='update'){
            this.updateArticle.emit();
          }});
    }

    getOneArticle(){
      this.ForumService.getOneArticle(this.article).subscribe(res=>{
        this.updateArticle.emit();
      })
    }

  
  

}
