import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FeedbackComponent } from './components/feedback/feedback.component';
import { PostComponent } from './components/post/post.component';
import { ListPostesComponent } from './components/list-postes/list-postes.component';
import { CommentComponent } from './components/comment/comment.component';
import { BadgeComponent } from './components/badge/badge.component';
import { TropheyComponent } from './components/trophey/trophey.component';
import { CongratsComponent } from './components/congrats/congrats.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDialogModule} from '@angular/material/dialog';
import { CongratsTComponent } from './components/congrats-t/congrats-t.component';
import { NgParticlesModule } from 'ng-particles';
import { ProfileVisiterComponent } from './profile-visiter/profile-visiter.component';
@NgModule({
  declarations: [
    AppComponent,
    FeedbackComponent,
    PostComponent,
    ListPostesComponent,
    CommentComponent,
    BadgeComponent,
    TropheyComponent,
    CongratsComponent,
    CongratsTComponent,
    ProfileVisiterComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    NgParticlesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
