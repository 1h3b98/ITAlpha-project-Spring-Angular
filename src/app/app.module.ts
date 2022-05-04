import {HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LuxonModule } from 'luxon-angular';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EventComponent } from './event/event.component';
import { AdminEventComponent } from './event/adminEvent.component';
import { EventDetailComponent } from './event-detail/event-detail.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AdminEventDetailComponent } from './event-detail/adminEvent-detail.component';
import { UserComponent } from './user/user.component';
import { FavoriteComponent } from './favorite/favorite.component';
import { LikeComponent } from './like/like.component';
import { JoinComponent } from './join/join.component';

const ROUTES: Routes=[
  {path: 'events',component: EventComponent},
  {path: 'event-detail/:Id',component: EventDetailComponent},
  {path: 'admin-event-detail/:Id',component: AdminEventDetailComponent},
  {path: 'user',component: UserComponent}
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
    JoinComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LuxonModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
