import { Component, OnInit ,Inject} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ForumService } from 'src/app/services/forum-service/forum.service';

@Component({
  selector: 'app-deleteforum-dialag',
  templateUrl: './deleteforum-dialag.component.html',
  styleUrls: ['./deleteforum-dialag.component.css']
})
export class DeleteforumDialagComponent implements OnInit {

  btnAction :string ='ok';
  constructor(private forumservice : ForumService,@Inject(MAT_DIALOG_DATA) public data  : any,private dialogRef : MatDialogRef<DeleteforumDialagComponent>) { }

  ngOnInit(): void {
  } 


  accept(){
    this.forumservice.deleteArticle(this.data).subscribe((Response)=>{
     console.log(this.data);
     this.dialogRef.close('ok');
    });
  }
 



}
