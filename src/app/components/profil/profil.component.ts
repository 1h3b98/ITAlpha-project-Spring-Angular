import { Component, OnInit } from '@angular/core';
import { Trophey } from 'src/app/model/Trophey';
import { TropheyService } from 'src/app/service/trophey-service/trophey.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

  constructor(private tropheyservice:TropheyService ) { }
  Listtrophey:Trophey[];
  id:number;
 
  ngOnInit(): void {
    this.tropheyservice.getTrophies(1).subscribe(
      (data1:Trophey[])=>this.Listtrophey=data1
    );
   console.log(this.Listtrophey) 
  }

  show(e:any) {

    let card = e.srcElement.children[0].children[2];
    card.classList.add('show');


  }

  leavep(e:any) {
    console.log(e);
    let card = e.srcElement.children[0].children[2];
    card.classList.remove('show');
  }

  leave(e:any) {
    let target = e.target;
    target.classList.remove('show');
  }

}
