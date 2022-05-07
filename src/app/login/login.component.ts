import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../Model/User';
import { AuthService } from '../Service/auth-service.service';
/*export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
*/
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
  //matcher = new MyErrorStateMatcher();
user=new User();
err:number=0;
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
   
  }

 onLoggedin()
 {
     this.authService.login(this.user).subscribe({
     next:(data)=>{
     let jwtToken=data.headers.get('Authorization')!;
     this.authService.saveToken(jwtToken);
     this.router.navigate(['/profil']);
   },error:(err:any)=>{
     this.err=1;
   }
 });
}
}

