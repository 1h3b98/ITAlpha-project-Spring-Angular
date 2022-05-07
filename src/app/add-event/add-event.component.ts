import { Component, OnInit } from '@angular/core';
import { Offre } from '../Model/Offre';
import { UserService } from '../Service/user.service';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent implements OnInit {
  offre:Offre[];
  constructor(private us:UserService) { }

  ngOnInit(): void {
    this.us.ListOffre().subscribe(ofre=>{
      console.log(ofre);
      this.offre=ofre;
    })
  }

}
