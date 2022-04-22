import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DeleteDialogComponent } from 'src/app/delete-dialog/delete-dialog.component';
import { env } from 'src/app/env/env';
import { Forum } from 'src/app/models/forum';
import { ForumService } from 'src/app/services/forum-service/forum.service';
import { AddForumComponent } from '../add-forum/add-forum.component';
import { DeleteforumDialagComponent } from '../deleteforum-dialag/deleteforum-dialag.component';

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent implements OnInit {
  tags :any
  ListTags:string[]
  @Input() article :Forum
  @Output() DeleteArticle = new EventEmitter<Forum>();
  @Output() updateArticle = new EventEmitter();
  constructor(public dialog: MatDialog , private http:HttpClient,private ForumService:ForumService) { }


  ngOnInit(): void {
    this.getTag()
  }
  openDialog() {
    this.dialog.open(AddForumComponent, {
    width:'30%',
    data:this.ListTags
    });
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
  getTag(){
    this.ForumService.getTags().subscribe((res)=>{
       this.ListTags=res
       console.log(this.ListTags)
    })}

    addToFavoris(article: Forum){
      this.ForumService.addToFavoris(article).subscribe()
    }
  

}
