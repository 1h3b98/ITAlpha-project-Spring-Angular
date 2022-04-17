import {HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LuxonModule } from 'luxon-angular';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EventComponent } from './event/event.component';
import { AdminEventComponent } from './event/adminEvent.component';
@NgModule({
  declarations: [
    AppComponent,
    EventComponent,
    AdminEventComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LuxonModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
