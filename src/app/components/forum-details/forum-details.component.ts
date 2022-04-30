import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Forum } from 'src/app/models/forum';
import { ForumService } from 'src/app/services/forum-service/forum.service';

@Component({
  selector: 'app-forum-details',
  templateUrl: './forum-details.component.html',
  styleUrls: ['./forum-details.component.css']
})
export class ForumDetailsComponent implements OnInit {

  UserImage:string ;
   article :Forum
  articleId:any;
  constructor(private activatedRute: ActivatedRoute,private articleService : ForumService) { }

  ngOnInit(): void {
   this.articleId= this.activatedRute.snapshot.paramMap.get('id');
   this.getOneForum();
   
   
  }

  getOneForum(){
    this.articleService.getOneArticle(this.articleId).subscribe(res=>{
      this.article=res
      console.log(this.article)
    })
  }

}
