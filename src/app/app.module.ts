import {HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EventComponent } from './event/event.component';
import { AdminEventComponent } from './event/adminEvent.component';
import { EventDetailComponent } from './event-detail/event-detail.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AdminEventDetailComponent } from './event-detail/adminEvent-detail.component';
import { UserComponent } from './favEvent/user.component';
import { FavoriteComponent } from './favorite/favorite.component';
import { LikeComponent } from './like/like.component';
import { JoinComponent } from './join/join.component';
import { APP_BASE_HREF } from '@angular/common';
import { MembersComponent } from './members/members.component';
import { InviteComponent } from './invite/invite.component';
import { AcceptComponent } from './accept/accept.component';
import { RefuseComponent } from './refuse/refuse.component';
import { CommentComponent } from './comment/comment.component';
import { InvMembersComponent } from './inv-members/inv-members.component';

const ROUTES: Routes=[
  {path: 'events',component: EventComponent},
  {path: 'event-detail/:Id',component: EventDetailComponent},
  {path: 'admin-event-detail/:Id',component: AdminEventDetailComponent},
  {path: 'favEvent',component: UserComponent}
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
    MembersComponent,
    InviteComponent,
    AcceptComponent,
    RefuseComponent,
    CommentComponent,
    InvMembersComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES),
    FormsModule
  ],
  providers: [{provide: APP_BASE_HREF, useValue: '/'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
