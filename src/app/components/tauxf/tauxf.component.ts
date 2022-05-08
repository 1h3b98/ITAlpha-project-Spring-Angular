import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { FeedbackService } from 'src/app/service/feedback-service/feedback.service';

@Component({
  selector: 'app-tauxf',
  templateUrl: './tauxf.component.html',
  styleUrls: ['./tauxf.component.css']
})
export class TauxfComponent implements OnInit {
listt:number[]
  constructor(private feedbackservice:FeedbackService) { }
chart:any;
  ngOnInit(): void {
    this.feedbackservice.gettauxF().subscribe( Response=>{
      this.listt=Response;
      console.log(this.listt)
      this.chart= new Chart('canvas',{
        type:'line',
        data:{
            labels:["January", "February", "March", "April", "May", "June", "July","August","September","October","November","December"],
            datasets:[
             { data:this.listt,
              borderColor:'#3cba9f',
              fill:false}
            ]
        }
      })
    })
    
  }



  getTauxf(){
    this.feedbackservice.gettauxF().subscribe( Response=>{
      this.listt=Response;
      
    })
  }
}
