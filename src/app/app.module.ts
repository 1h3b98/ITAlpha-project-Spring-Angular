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
import { ListfeedsComponent } from './components/listfeeds/listfeeds.component';
import { FeedsComponent } from './components/feeds/feeds.component';
import { FeedssentComponent } from './components/feedssent/feedssent.component';
import { ChartpointsComponent } from './components/chartpoints/chartpoints.component';
import { StepprogressbarComponent } from './components/stepprogressbar/stepprogressbar.component';
import { FilterFeedbackPipe } from './pipes/filter-feedback.pipe';
import { LineChartComponent } from './components/line-chart/line-chart.component';
import { TriUsersComponent } from './components/tri-users/tri-users.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
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
    ListfeedsComponent,
    FeedsComponent,
    FeedssentComponent,
    ChartpointsComponent,
    StepprogressbarComponent,
    FilterFeedbackPipe,
    LineChartComponent,
    TriUsersComponent,
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    NgParticlesModule,
    MatProgressBarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
