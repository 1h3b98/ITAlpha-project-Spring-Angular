import { Component, Input, OnInit } from '@angular/core';

import { Trophey } from 'src/app/models/Trophey';
import { TropheyService } from 'src/app/services/trophey-service/trophey.service';

@Component({
  selector: 'app-trophey',
  templateUrl: './trophey.component.html',
  styleUrls: ['./trophey.component.css']
})
export class TropheyComponent implements OnInit {
  
  @Input() ttrophey : Trophey;
  trophey:Trophey;

  constructor(private tropheyservice : TropheyService) { 

  }
  

  ngOnInit(): void {
   
  }


 



 

}
