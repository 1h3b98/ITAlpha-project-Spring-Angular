import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { map, Observable, startWith } from 'rxjs';
import { tag } from 'src/app/model/tag';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {COMMA, ENTER} from '@angular/cdk/keycodes';


@Component({
  selector: 'app-add-forum',
  templateUrl: './add-forum.component.html',
  styleUrls: ['./add-forum.component.css']
})
export class AddForumComponent implements OnInit {
  separatorKeysCodes: number[] = [ENTER, COMMA];
  articleCtrl = new FormControl();
  filteredArticles: Observable<string[]>;
  articles: string[] = [];
  allArticles: string[] = ['bessem'];
  title:string
  content:string
  str : any
  @ViewChild('articleInput') articleInput: ElementRef<HTMLInputElement>;

  ngOnInit(): void {

    this.gettags();
    this.allArticles=this.data
    console.log( this.allArticles+"hhhhhhhhhhhh")
   
  }
  constructor(private http:HttpClient ,@Inject(MAT_DIALOG_DATA) public data : any,  private dialogRef : MatDialogRef<AddForumComponent>) {
    this.filteredArticles = this.articleCtrl.valueChanges.pipe(
      startWith(null),
      map((article: string | null) => (article ? this._filter(article) : this.allArticles.slice())),
    );
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.articles.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();

    this.articleCtrl.setValue(null);
  }

  remove(article: string): void {
    const index = this.articles.indexOf(article);

    if (index >= 0) {
      this.articles.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.articles.push(event.option.viewValue);
    this.articleInput.nativeElement.value = '';
    this.articleCtrl.setValue(null);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.allArticles.filter(article => article.toLowerCase().includes(filterValue));
  }



  addforum(){
    this.http.post("http://localhost:8089/SpringMVC/forum/addarticle/"+this.title+"/"+this.content,this.articles)
    .subscribe(res=>{
      console.log(this.articles);
      console.log("done")
      this.dialogRef.close('update');
      
    })
    }

    gettags(){
     return this.http.get<string[]>("http://localhost:8089/SpringMVC/forum/gettags").subscribe((data)=>{
       console.log(this+"ffffffffffffffff")
     })
    }
  }




