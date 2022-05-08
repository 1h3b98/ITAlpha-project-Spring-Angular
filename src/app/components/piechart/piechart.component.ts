import { Component, OnInit } from '@angular/core';
import { PiechartService } from 'src/app/service/PieChart-service/piechart.service';

@Component({
  selector: 'app-piechart',
  templateUrl: './piechart.component.html',
  styleUrls: ['./piechart.component.css']
})
export class PiechartComponent implements OnInit {
public data:Object[];
public chartTitle:string;
public chartLabel : object;
public legend : object;
public tootlipSettings : object;

  constructor(private piechartservice:PiechartService) {
    this.getevents();
    this.chartLabel = {
      visible:true,
      position:'Inside',
      name:'text'
    }
    this.legend = {
      visible:true,
      position:'Bottom',
      height:'8%',
      width:'35%'
    }
    this.tootlipSettings = {
     enable: true,
     format : '${point.x} : <b>${point.y}%</b>'
    }

   }

  ngOnInit(): void {
    this.getevents()
    this.chartTitle="Events Participation Rate"
  }
  

  getevents(){
    return this.piechartservice.getevents().subscribe(
      Response=>{
        this.data=Response;
        console.log(Response)
        

        
      }
    );
    
  }

    
  }


