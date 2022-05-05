import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';
import { userBadge } from 'src/app/models/userBadge';
import { FeedbackService } from 'src/app/services/feedback-service/feedback.service';
@Component({
  selector: 'app-chartpoints',
  templateUrl: './chartpoints.component.html',
  styleUrls: ['./chartpoints.component.css']
})
export class ChartpointsComponent implements OnInit {
  listUB: userBadge[];

  constructor(private feedbackservice:FeedbackService) { }
data:any;
datan:any;
  ngOnInit(): void {
    this.feedbackservice.getUsers().subscribe( Response=>{
      this.data=Response;
      this.feedbackservice.getUsersnames().subscribe( Response=>{
        this.datan=Response;
        console.log(this.datan);
        
      console.log(this.data); var myChart = new Chart("myChart", {
        type: 'bar',
        data: {
          labels: this.datan,
          datasets: [{
            label: '# of points',
            data: this.data,
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
          }]
        },
        options: {
          scales: {
            y: {
              beginAtZero: true
            }
          }
        }
      });
      }
     );
    }
   );
    
   
  }

getpoints(){
  return this.feedbackservice.getUsers().subscribe( Response=>{
    this.data=Response;
    console.log(this.data)
  }
 );
}
getnames(){
  return this.feedbackservice.getUsersnames().subscribe( Response=>{
    this.datan=Response;
    console.log(this.datan)
  }
 );
}


}
