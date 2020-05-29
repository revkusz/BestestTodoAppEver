import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TodotableComponent } from './todotable/todotable.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { MainheaderComponent } from './mainheader/mainheader.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginbuttonComponent } from './loginbutton/loginbutton.component';
import { FormsModule } from '@angular/forms';
import { EventEmitterService } from './event-emitter.service';

@NgModule({
  declarations: [
    AppComponent,
    TodotableComponent,
    MainheaderComponent,
    LoginbuttonComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [EventEmitterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
