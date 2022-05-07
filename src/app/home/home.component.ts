import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../Model/User';
import { UserService } from '../Service/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
user= new User();
  constructor(private route:Router,private us:UserService) { }

  ngOnInit(): void {
    
  }
  profil(): void {
 
    this.route.navigate(['/profil'])

  }
  editProfil():void{
    this.route.navigate(['/editprofil'])
  }
  event():void{
    this.route.navigate(['/event'])
  }
}
