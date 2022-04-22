import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { map, Observable, startWith } from 'rxjs';
import { tag } from 'src/app/models/tag';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {COMMA, ENTER} from '@angular/cdk/keycodes';


@Component({
  selector: 'app-add-forum',
  templateUrl: './add-forum.component.html',
  styleUrls: ['./add-forum.component.css']
})
export class AddForumComponent implements OnInit {
  separatorKeysCodes: number[] = [ENTER, COMMA];
  fruitCtrl = new FormControl();
  filteredFruits: Observable<string[]>;
  fruits: string[] = [];
  allFruits: string[] = [];
  title:string
  content:string
  str : any
 


  @ViewChild('fruitInput') fruitInput: ElementRef<HTMLInputElement>;


  ngOnInit(): void {

    this.gettags();
    this.allFruits=this.data
    console.log(this.data+"hhhhhhhhhhhh")
   
  }
  constructor(private http:HttpClient ,@Inject(MAT_DIALOG_DATA) public data : any) {
    this.filteredFruits = this.fruitCtrl.valueChanges.pipe(
      startWith(null),
      map((fruit: string | null) => (fruit ? this._filter(fruit) : this.allFruits.slice())),
    );
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.fruits.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();

    this.fruitCtrl.setValue(null);
  }

  remove(fruit: string): void {
    const index = this.fruits.indexOf(fruit);

    if (index >= 0) {
      this.fruits.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.fruits.push(event.option.viewValue);
    this.fruitInput.nativeElement.value = '';
    this.fruitCtrl.setValue(null);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.allFruits.filter(fruit => fruit.toLowerCase().includes(filterValue));
  }



  addforum(){
    this.http.post("http://localhost:8089/SpringMVC/forum/addarticle/"+this.title+"/"+this.content,this.fruits)
    .subscribe(res=>{
      console.log("added");
      
    })
    }

    gettags(){
     return this.http.get<string[]>("http://localhost:8089/SpringMVC/forum/gettags").subscribe((data)=>{
       this.str=data;
 
       this.fruits.push.apply(this.str)
       console.log(data)
     })
    }
  }





