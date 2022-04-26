import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { PostService } from 'src/app/services/postService/post-service.service';

@Component({
  selector: 'app-memories',
  templateUrl: './memories.component.html',
  styleUrls: ['./memories.component.css']
})
export class MemoriesComponent implements OnInit {
ListPosts:Post[];
  constructor(private PostService:PostService) { }

  ngOnInit(): void {
    this.PostService.getMemories().subscribe(res=>{
      this.ListPosts=res
      console.log(res)
    })
  }
}
