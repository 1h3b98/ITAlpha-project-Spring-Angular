import { Component, Input, OnInit } from '@angular/core';
import { comment } from 'src/app/models/Comment';
import { CommentServiceService } from 'src/app/services/comment-sercice/comment-service.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  ListComment : comment[];
  @Input() comment : comment;
  constructor(private  CommentService: CommentServiceService) {  
    
  }
  ngOnInit(): void {
  
  }

}