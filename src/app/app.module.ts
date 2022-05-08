import {HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EventComponent } from './event/event.component';
import { AdminEventComponent } from './event/adminEvent.component';
import { EventDetailComponent } from './event-detail/event-detail.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminEventDetailComponent } from './event-detail/adminEvent-detail.component';
import { UserComponent } from './user/user.component';
import { FavoriteComponent } from './favorite/favorite.component';
import { LikeComponent } from './like/like.component';
import { JoinComponent } from './join/join.component';
import { APP_BASE_HREF } from '@angular/common';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatMenuModule} from '@angular/material/menu';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';
import {MatBadgeModule} from '@angular/material/badge';
import {MatIconModule} from '@angular/material/icon';
import {MatChipsModule} from '@angular/material/chips';
import { ListPostesComponent } from './components/list-postes/list-postes.component';
import { ListForumComponent } from './components/list-forum/list-forum.component';
import { ForumDetailsComponent } from './components/forum-details/forum-details.component';
import { ListFavorisComponent } from './components/list-favoris/list-favoris.component';
import { BirthdayComponent } from './components/birthday/birthday.component';
import { PostComponent } from './components/post/post.component';
import { CommentComponent } from './components/comment/comment.component';
import { MemoriesComponent } from './components/memories/memories.component';
import { SignalerComponent } from './components/signaler/signaler.component';
import { DeleteDialogComponent } from './components/delete-dialog/delete-dialog.component';
import { UpdateComponent } from './components/update/update.component';
import { RatingComponent } from './components/rating/rating.component';
import { CreatePostComponent } from './components/create-post/create-post.component';
import { AddForumComponent } from './components/add-forum/add-forum.component';
import { ForumComponent } from './components/forum/forum.component';
import { OpinionComponent } from './components/opinion/opinion.component';
import { DeleteforumDialagComponent } from './components/deleteforum-dialag/deleteforum-dialag.component';
import { ProfilComponent } from './components/profil/profil.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {AccumulationChartModule , PieSeriesService, AccumulationDataLabelService,AccumulationLegendService,AccumulationTooltipService} from '@syncfusion/ej2-angular-charts';
import { BadgeComponent } from './components/badge/badge.component';
import { ChartpointsComponent } from './components/chartpoints/chartpoints.component';
import { CongratsComponent } from './components/congrats/congrats.component';
import { CongratsTComponent } from './components/congrats-t/congrats-t.component';
import { FeedbackComponent } from './components/feedback/feedback.component';
import { FeedsComponent } from './components/feeds/feeds.component';
import { FeedssentComponent } from './components/feedssent/feedssent.component';
import { LineChartComponent } from './components/line-chart/line-chart.component';
import { ListfeedsComponent } from './components/listfeeds/listfeeds.component';
import { PiechartComponent } from './components/piechart/piechart.component';
import { StepprogressbarComponent } from './components/stepprogressbar/stepprogressbar.component';
import { TriUsersComponent } from './components/tri-users/tri-users.component';
import { TropheyComponent } from './components/trophey/trophey.component';
import { FilterFeedbackPipe } from './pipes/filter-feedback.pipe';
import { NgParticlesModule } from 'ng-particles';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { TitlesComponent } from './components/titles/titles.component';
import { MatCardModule } from '@angular/material/card';
import { TauxfComponent } from './components/tauxf/tauxf.component';

const ROUTES: Routes=[
  {path: 'events',component: EventComponent},
  {path: 'event-detail/:Id',component: EventDetailComponent},
  {path: 'admin-event-detail/:Id',component: AdminEventDetailComponent},
  {path: 'user',component: UserComponent},
  {path:'posts', component: ListPostesComponent},
  {path:'forums',component:ListForumComponent},
  {path:'forums/articleDetail/:id',component:ForumDetailsComponent},
  {path:'forum/favoris',component:ListFavorisComponent},
  {path:'birthday',component:BirthdayComponent} 
]
@NgModule({
  declarations: [
    AppComponent,
    EventComponent,
    AdminEventComponent,
    EventDetailComponent,
    AdminEventDetailComponent,
    UserComponent,
    FavoriteComponent,
    LikeComponent,
    JoinComponent,
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
    CreatePostComponent,
    RatingComponent,
    BirthdayComponent,
    ForumDetailsComponent,
    OpinionComponent,
    ListFavorisComponent,
    DeleteforumDialagComponent,
    ProfilComponent,
    BadgeComponent,
    ChartpointsComponent,
    CongratsComponent,
    CongratsTComponent,
    FeedbackComponent,
    FeedsComponent,
    FeedssentComponent,
    LineChartComponent,
    ListfeedsComponent,
    PiechartComponent,
    StepprogressbarComponent,
    TriUsersComponent,
    TropheyComponent,
    FilterFeedbackPipe,
    DashboardComponent,
    TitlesComponent,
    TauxfComponent,

    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES),
    FormsModule,
    Ng2SearchPipeModule,
    NgbModule,
    MatAutocompleteModule,
    MatMenuModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    MatBadgeModule,
    MatIconModule,
    MatChipsModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressBarModule,
    AccumulationChartModule,
    NgParticlesModule,
    MatCardModule



    
  ],
  providers: [{provide: APP_BASE_HREF, useValue: '/'},PieSeriesService,AccumulationDataLabelService,AccumulationLegendService,AccumulationTooltipService],
  bootstrap: [AppComponent]
})
export class AppModule { }
