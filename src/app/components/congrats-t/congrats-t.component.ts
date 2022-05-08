import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Trophey } from 'src/app/model/Trophey';
import { CongratsTService } from 'src/app/service/congratsT-service/congrats-t.service';
import { TropheyService } from 'src/app/service/trophey-service/trophey.service';

@Component({
  selector: 'app-congrats-t',
  templateUrl: './congrats-t.component.html',
  styleUrls: ['./congrats-t.component.css']
})
export class CongratsTComponent implements OnInit {
  trophey:Trophey;
  constructor(private tropheyservice:TropheyService,private congratsTservice : CongratsTService, @Inject(MAT_DIALOG_DATA )public data :any) { }

  ngOnInit(): void {
    this.trophey=this.data;
  }

}