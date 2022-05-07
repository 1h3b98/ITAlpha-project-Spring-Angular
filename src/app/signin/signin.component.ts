import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute,ParamMap} from '@angular/router';
import { User } from '../Model/User';
import { UserService } from '../Service/user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
   user: User  =new  User();
  constructor(private us:UserService, private router:Router){}
  ngOnInit(): void {}

  addUser(){
    this.us.addUser(this.user).subscribe();
    }
}
