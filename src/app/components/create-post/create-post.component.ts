import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/services/postService/post-service.service';
import {MatDialog,MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DeleteforumDialagComponent } from '../deleteforum-dialag/deleteforum-dialag.component';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

 
  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  cont : string = '';
  constructor(private postservice : PostService , private httpClient: HttpClient,private dialogRef : MatDialogRef<DeleteforumDialagComponent>) { }

  ngOnInit(): void {
  }
  
  //Gets called when the user selects an image
public onFileChanged(event : any) {
  //Select File
  this.selectedFile = event.target.files[0];
}
 //Gets called when the user clicks on submit to upload the image
 onUpload() {
  console.log(this.selectedFile);
  
  //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
  const uploadImageData = new FormData();
  uploadImageData.append('file', this.selectedFile, this.selectedFile.name);

  //Make a call to the Spring Boot Application to save the image
  this.httpClient.post('http://localhost:8089/SpringMVC/post/add/'+this.cont+'/', uploadImageData, { observe: 'response' })
    .subscribe((response) => {
      if (response.status === 200) {
        console.log(this.cont);
        this.dialogRef.close('ok');
      } else {
        this.message = 'Image not uploaded successfully';
      }
      
    }

    );
      console.log(this.cont);

}
  //Gets called when the user clicks on retieve image button to get the image from back end
  getImage() {
    //Make a call to Sprinf Boot to get the Image Bytes.
    this.httpClient.get('http://localhost:8082/SpringMVC/post/getimage/' + this.imageName)
      .subscribe(
        res => {
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,'+ this.base64Data;
        }
      );
  }



}
