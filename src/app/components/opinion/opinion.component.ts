import { Component, Input, OnInit, Output } from '@angular/core';
import { opinion } from 'src/app/models/opinion';
import { OpinionService } from 'src/app/services/opinionService/opinion.service';
import {EventEmitter} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-opinion',
  templateUrl: './opinion.component.html',
  styleUrls: ['./opinion.component.css']
})
export class OpinionComponent implements OnInit {

  constructor(private formBuilder : FormBuilder,private OpinionService:OpinionService) { }
  opinionForum !:FormGroup;
  showUpdate :boolean=true;
  UserImage:any
  @Input() opinion:opinion;
  @Output() refresh = new EventEmitter();
  ngOnInit(): void {
    this.UserImage='data:image/jpeg;base64,'+this.opinion.photo 
    this.opinionForum= this.formBuilder.group({
      content :['',Validators.required]
    })
  }
  up(){
    this.OpinionService.up(this.opinion.idOpinion).subscribe(res=>{
      console.log(res)
    this.refresh.emit();
    })
  }
  down(){
    this.OpinionService.down(this.opinion.idOpinion).subscribe(res=>{
      
      console.log(res)
      this.refresh.emit();
    })
  }
  get(){
    this.showUpdate=false
    this.opinionForum.controls['content'].setValue(this.opinion.content);
  }
  delete(){
    this.OpinionService.deleteOpinion(this.opinion.idOpinion).subscribe(res=>{
      this.refresh.emit();
    })
  }
  updateopinion(opinion:opinion){
this.OpinionService.updateopinion(this.opinionForum.value,opinion).subscribe(res=>{
  this.refresh.emit();
})
  }

}
