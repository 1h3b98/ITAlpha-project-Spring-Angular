import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListPostComponent } from './Components/list-post/list-post.component';
import { ListForumComponent } from './Components/list-forum/list-forum.component';
import { PostComponent } from './Components/post/post.component';
import { CommentComponent } from './Service/comment/comment.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    ListPostComponent,
    ListForumComponent,
    CommentComponent,
    PostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule ,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
