import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CongratsService {
  date1 = formatDate(new Date(),'yyyy-MM-dd','en_US');
  constructor(private http : HttpClient) { }

checkDate(date:Date):boolean
{
 
  
  console.log(this.date1)
  if(formatDate(date,'yyyy-MM-dd','en_US')==this.date1)
  return true;
  return false; 
}

}
