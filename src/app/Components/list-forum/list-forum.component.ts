import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/Service/PostService/post.service';

@Component({
  selector: 'app-list-forum',
  templateUrl: './list-forum.component.html',
  styleUrls: ['./list-forum.component.css']
})
export class ListForumComponent  {

  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  
  constructor(private  postservice: PostService, private httpClient: HttpClient) { }

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
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
  
    //Make a call to the Spring Boot Application to save the image
    this.httpClient.post('http://localhost:8082/SpringMVC/post/add/tesr', uploadImageData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      }
      );


  }
    //Gets called when the user clicks on retieve image button to get the image from back end
    getImage() {
      //Make a call to Sprinf Boot to get the Image Bytes.
      this.httpClient.get('http://localhost:8080/image/get/' + this.imageName)
        .subscribe(
          res => {
            this.retrieveResonse = res;
            this.base64Data = this.retrieveResonse.picByte;
            this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
          }
        );
    }
}
