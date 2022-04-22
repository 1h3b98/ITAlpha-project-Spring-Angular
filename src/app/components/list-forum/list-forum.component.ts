import { Component, OnInit } from '@angular/core';
import { Forum } from 'src/app/models/forum';
import { tag } from 'src/app/models/tag';
import { ForumService } from 'src/app/services/forum-service/forum.service';

@Component({
  selector: 'app-list-forum',
  templateUrl: './list-forum.component.html',
  styleUrls: ['./list-forum.component.css']
})
export class ListForumComponent implements OnInit {
  forum :Forum
  ListArticle : Forum[]
  
  constructor(private  ForumService: ForumService) { }

  ngOnInit(): void {
    this.forum = new Forum();
    this.getArticle(); 
    
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


}
