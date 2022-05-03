import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { PostService } from 'src/app/services/postService/post-service.service';
import {MatDialog,MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { CreatePostComponent } from '../create-post/create-post.component';

import { ShepherdService } from "angular-shepherd";
import { UserServiceService } from 'src/app/services/userService/user-service.service';

const tour = {
  defaultStepOptions: {
    scrollTo: true,
    cancelIcon: {
      enabled: true
    },
    canClickTarget: true,
    popperOptions: {
      modifiers: [
        {
          name: "offset",
          options: {
            offset: [0, 20]
          }
        }
      ]
    },
    modalOverlayOpeningPadding: 8,
    modalOverlayOpeningRadius: 4
  },
  st: [
    {
      id: "btn-1",
      attachTo: {
        element: ".home-btn-1",
        on: "bottom"
      },
      buttons: [
        {
          classes: "cancel-button",
          text: "Exit"
        },
        {
          classes: "shepherd-button-primary",
          text: "Back"
        },
        {
          classes: "shepherd-button-primary",
          text: "Next"
        }
      ],
      classes: "",
      highlightClass: "highlight",
      title: "Step 1",
      text: "This is content"
    },
    {
      id: "btn-2",
      attachTo: {
        element: ".home-btn-2",
        on: "bottom"
      },
      buttons: [
        {
          classes: "mat-raised-button",
          text: "Exit"
        },
        {
          classes: "mat-raised-button",
          text: "Back",
          
        },
        {
          classes: "mat-raised-button",
          text: "Next",
          
        }
      ],
      classes: "",
      highlightClass: "highlight",
      title: "Step 2",
      text: "This is content"
    }
  ]
};


@Component({
  selector: 'app-list-postes',
  templateUrl: './list-postes.component.html',
  styleUrls: ['./list-postes.component.css']
})
export class ListPostesComponent implements OnInit ,OnDestroy, AfterViewInit  {
  Listposts : Post[];
  ListUserbirthday:Post[];
 post:Post;
 
  constructor(private postservice : PostService,private guideTour:ShepherdService ,
   private userservice:UserServiceService, private httpClient: HttpClient,public dialog: MatDialog) { }
  ngOnDestroy() {

  
  }

  ngAfterViewInit() {}

  ngOnInit(): void {
    this.post= new Post();
   this.getPosts();
   this.startTour();
   this.birthday()
  }

  getPosts(){
    this.postservice.getPosts().subscribe(
      (data:Post[])=>{
        this.Listposts=data
        console.log(data)
      }
    );
  } 
  deletePost(u: Post){
    this.Listposts= this.Listposts.filter((e: Post)=>{
      return e.idPost!=u.idPost;   
  });}


  openDialog(){
    this.dialog.open(CreatePostComponent, {
      width:'50%'
      }).afterClosed().subscribe(val=>{
        if(val==='ok'){
          this.getPosts();
        }
      })
  }
  addPostWithoutPhoto(){
    this.postservice.addPostWithoutPhoto(this.post).subscribe(res=>{
      console.log(this.post.content)
      this.getPosts();
    })
  }
  
  startTour() {
    this.guideTour.defaultStepOptions = tour.defaultStepOptions;
    this.guideTour.modal = true;
    this.guideTour.confirmCancel = false;
    this.guideTour.addSteps([ {
      title: 'Test details',
      text: `You can find test details here`,
      attachTo: {
        element: '.tt',
        on: 'bottom'
      },
      classes: 'shepherd shepherd-open shepherd-theme-arrows shepherd-transparent-text shepherd-step-highlight',

      buttons: [
        {
          action() {
            return this.next();
          },
          text: 'Next'
        }
      ],
      id: 'creating'
    },{
      title: 'Namecard',
      text: `you can add `,
      attachTo: {
        element: '.bb',
        on: 'bottom'
      },
      classes: 'shepherd shepherd-open shepherd-theme-arrows shepherd-transparent-text',

      buttons: [
        {
          action() {
            return this.back();
          },
          classes: 'shepherd-button-secondary',
          text: 'Back'
        },
        {
          action() {
            return this.next();
          },
          text: 'Next'
        }
      ],
      id: 'creating'
    },]);
    this.guideTour.start();
  }

  birthday(){
    this.userservice.birthday().subscribe(res=>{
      this.ListUserbirthday=res;
    })
  }


}
