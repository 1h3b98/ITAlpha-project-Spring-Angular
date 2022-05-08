import { Component, Input, OnInit } from '@angular/core';

import { Post } from 'src/app/model/Post';
import { UserServiceService } from 'src/app/service/userService/user-service.service';

@Component({
  selector: 'app-birthday',
  templateUrl: './birthday.component.html',
  styleUrls: ['./birthday.component.css']
})
export class BirthdayComponent implements OnInit {
@Input() post:Post  
ListUserbirthday:Post[]
UserImage:string='data:image/jpeg;base64,'
  constructor(private userservice:UserServiceService) { }

  ngOnInit(): void {
    this.birthday()  
  }
  birthday(){
    this.userservice.birthday().subscribe(res=>{
      this.ListUserbirthday=res;
      console.log( res)
      this.ListUserbirthday.forEach(element => {
        element.photo=this.UserImage+element.photo
        console.log( element.photo)
      });
     
    })
  }

}
