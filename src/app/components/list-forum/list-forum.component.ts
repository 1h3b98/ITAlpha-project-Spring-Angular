import { Component, OnInit } from '@angular/core';
import { Forum } from 'src/app/models/forum';
import { tag } from 'src/app/models/tag';
import { ForumService } from 'src/app/services/forum-service/forum.service';
import { MatDialog } from '@angular/material/dialog';
import { AddForumComponent } from '../add-forum/add-forum.component';

@Component({
  selector: 'app-list-forum',
  templateUrl: './list-forum.component.html',
  styleUrls: ['./list-forum.component.css']
})
export class ListForumComponent implements OnInit {
  forum :Forum
  ListArticle : Forum[]
  ListTags:string[]
  
  constructor(public dialog: MatDialog ,private  ForumService: ForumService) { }

  ngOnInit(): void {
    this.forum = new Forum();
    this.getArticle(); 
    this.getTag()
    
  }

  hidden = false;

  toggleBadgeVisibility() {
    this.hidden = !this.hidden;
  }
  getAllAricles(){
   
  }
  addArticle(){
    this.ForumService.addArticle(this.forum).subscribe((response) => {
      console.log(response);
  });
  }
  getArticle(){
    this.ForumService.getAllAricles().subscribe(res=>{
      console.log("get all");
      this.ListArticle=res
    })
  }
  deleteArticle(u: Forum){
    this.ListArticle= this.ListArticle.filter((e: Forum)=>{
      return e.idForum!=u.idForum;   
  });}

  openDialog() {
    this.dialog.open(AddForumComponent, {
    width:'30%',
    data:this.ListTags
    });
  }
  getTag(){
    this.ForumService.getTags().subscribe((res)=>{
       this.ListTags=res
       console.log(this.ListTags)
    })}


}
