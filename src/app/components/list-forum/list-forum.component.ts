import { Component, OnInit } from '@angular/core';
import { Forum } from 'src/app/models/forum';
import { tag } from 'src/app/models/tag';
import { ForumService } from 'src/app/services/forum-service/forum.service';
import { MatDialog } from '@angular/material/dialog';
import { AddForumComponent } from '../add-forum/add-forum.component';
import { Pipe, PipeTransform } from '@angular/core';

@Component({
  selector: 'app-list-forum',
  templateUrl: './list-forum.component.html',
  styleUrls: ['./list-forum.component.css']
})
export class ListForumComponent implements OnInit {
  forum :Forum
  ListArticle : Forum[]
  ListTagArticles : Forum[]
  ListTags:string[]
  ListTagsDetails:tag[]
  ListTagsByOne:string[]
  searchtext:any
  
  constructor(public dialog: MatDialog ,private  ForumService: ForumService) { }

  ngOnInit(): void {
    this.forum = new Forum();
    this.getArticle(); 
    this.getTag();
    this. gettagDetails()
    
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

  newForum() {
    this.dialog.open(AddForumComponent, {
    width:'30%',
    data:this.ListTags
    }).afterClosed().subscribe(val=>{
      if(val==='update'){
        this.getArticle(); 
        this.getTag();
    this. gettagDetails()
      }});
  }
  getTag(){
    this.ForumService.getTags().subscribe((res)=>{
       this.ListTags=res
       console.log(this.ListTags)
    })}

    gettagDetails(){
      this.ForumService.getTagDetails().subscribe(res=>{
        this.ListTagsDetails=res
      })
    }

    getTagArticles(id:number){
      this.ForumService.getTagArticles(id).subscribe(res=>{
       this.ListArticle=res;
       console.log(res)
      })
    }
    get(i: number){
      this.getTagArticles(this.ListTagsDetails[i].tagId)
      
    }

}
