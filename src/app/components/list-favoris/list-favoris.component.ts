import { Component, OnInit } from '@angular/core';
import { Forum } from 'src/app/models/forum';
import { ForumService } from 'src/app/services/forum-service/forum.service';

@Component({
  selector: 'app-list-favoris',
  templateUrl: './list-favoris.component.html',
  styleUrls: ['./list-favoris.component.css']
})
export class ListFavorisComponent implements OnInit {

  constructor(private forumservice:ForumService) { }
  ListFavoris: Forum[]
  ngOnInit(): void {
    this. getFavoris();
  }

  getFavoris(){
    this.forumservice.getFavoris().subscribe(res=>{
      this.ListFavoris=res
    })
  }
}
