import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import {MatMenuModule} from '@angular/material/menu';
import { AppComponent } from './app.component';
import { FeedbackComponent } from './components/feedback/feedback.component';
import { PostComponent } from './components/post/post.component';
import { ListPostesComponent } from './components/list-postes/list-postes.component';
import { CommentComponent } from './components/comment/comment.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MemoriesComponent } from './components/memories/memories.component';
import {MatDialogModule} from '@angular/material/dialog';
import { SignalerComponent } from './components/signaler/signaler.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';
import { DeleteDialogComponent } from './components/delete-dialog/delete-dialog.component';
import { UpdateComponent } from './components/update/update.component';
import { ListForumComponent } from './components/list-forum/list-forum.component';
import { ForumComponent } from './components/forum/forum.component';
import { AddForumComponent } from './components/add-forum/add-forum.component';
import {MatBadgeModule} from '@angular/material/badge';
import {MatIconModule} from '@angular/material/icon';
import {MatChipsModule} from '@angular/material/chips';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { DeleteforumDialagComponent } from './components/deleteforum-dialag/deleteforum-dialag.component';
import { CreatePostComponent } from './components/create-post/create-post.component';
import { RatingComponent } from './components/rating/rating.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BirthdayComponent } from './components/birthday/birthday.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { RouterModule, Routes } from '@angular/router';
import { ForumDetailsComponent } from './components/forum-details/forum-details.component';
import { OpinionComponent } from './components/opinion/opinion.component';
import { ListFavorisComponent } from './components/list-favoris/list-favoris.component';


const ROUTES :Routes=[
  {path:'posts', component: ListPostesComponent},
  {path:'forums',component:ListForumComponent},
  {path:'forums/articleDetail/:id',component:ForumDetailsComponent},
  {path:'forum/favoris',component:ListFavorisComponent} 
  
]

@NgModule({
  declarations: [
    AppComponent,
    FeedbackComponent,
    PostComponent,
    ListPostesComponent,
    CommentComponent,
    MemoriesComponent,
    SignalerComponent,
    DeleteDialogComponent,
    UpdateComponent,
    ListForumComponent,
    ForumComponent,
    AddForumComponent,
    DeleteforumDialagComponent,
    CreatePostComponent,
    RatingComponent,
    BirthdayComponent,
    ForumDetailsComponent,
    OpinionComponent,
    ListFavorisComponent,
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    ReactiveFormsModule,
    MatBadgeModule,
    MatChipsModule,
    MatIconModule,
    MatAutocompleteModule,
    NgbModule,
    Ng2SearchPipeModule ,
    RouterModule.forRoot(ROUTES),
    MatMenuModule

    
  ], 
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 
  
}
