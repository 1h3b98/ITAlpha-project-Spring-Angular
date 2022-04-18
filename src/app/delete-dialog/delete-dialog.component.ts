import { Component, OnInit ,Inject} from '@angular/core';
import { PostService } from '../services/postService/post-service.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.css']
})
export class DeleteDialogComponent implements OnInit {
  btnAction :string ='ok';
  constructor(private postservice : PostService,@Inject(MAT_DIALOG_DATA) public data : any,private dialogRef : MatDialogRef<DeleteDialogComponent>) { }

  ngOnInit(): void {
  }

  decline(){

  }
  accept(){
    this.postservice.deletepost(this.data).subscribe((Response)=>{
     console.log(this.data);
     this.dialogRef.close('ok');
    });
  }
  dismiss(){
    
  }


}
