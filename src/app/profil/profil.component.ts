import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../Model/User';
import { AuthService } from '../Service/auth-service.service';
import { UserService } from '../Service/user.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  user:User[];
 
  constructor( private us: UserService,private auth:AuthService,private router:Router) { }

  ngOnInit(): void {
    this.us.getAlluser().subscribe(usser=>{
      console.log(usser);
      this.user=usser;
    })
  
  }
  logout() {
    this.auth.signOut();
    this.router.navigate(['/login']);
  }
  users(){
    this.us.getAlluser().subscribe(usser=>{
      console.log(usser);
      this.user=usser;
      this.router.navigate(['/dashbord']);
    })
  }
  todashbord(){
    this.router.navigate(['dashbord']);
  }


}
