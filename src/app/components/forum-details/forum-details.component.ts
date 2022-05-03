import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Forum } from 'src/app/models/forum';
import { opinion } from 'src/app/models/opinion';
import { ForumService } from 'src/app/services/forum-service/forum.service';
import { OpinionService } from 'src/app/services/opinionService/opinion.service';

@Component({
  selector: 'app-forum-details',
  templateUrl: './forum-details.component.html',
  styleUrls: ['./forum-details.component.css']
}) 
export class ForumDetailsComponent implements OnInit {

  UserImage:string ;
   article :Forum
  articleId:any;
  opinion:opinion
  opinionList:opinion[]
  similarArticle:Forum[]
  constructor(private formBuilder : FormBuilder,private activatedRute: ActivatedRoute,private OpinionService:OpinionService, private articleService : ForumService) { }

  ngOnInit(): void {
    this.opinion=new opinion()
   this.articleId= this.activatedRute.snapshot.paramMap.get('id');
   this.getOneForum();
   this.getOpinion()
   this.getsimilarArticle();
  
   
  }

  getOneForum(){
    this.articleService.getOneArticle(this.articleId).subscribe(res=>{
      this.article=res
      console.log(this.article)
      this.UserImage='data:image/jpeg;base64,'+this.article.photo  
    })
  }
  getOpinion(){
    this.OpinionService.getOpinionByArticle(this.articleId).subscribe(res=>{
      this.opinionList=res
      console.log(this.opinionList)
    })
  }

  addOpinion(){
    this.OpinionService.addOpinion(this.opinion , this.articleId).subscribe(res=>{
      console.log("done")
      this.getOpinion()
    })
  }

  getsimilarArticle(){
    this.articleService.getSimilarPost().subscribe(res=>{
      this.similarArticle=res;
      console.log(res)
    })
  }

}
