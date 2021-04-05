import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PlayComponent } from './play/play.component';
import {HeaderComponent} from './header/header.component';
import {FormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';



@NgModule({
    declarations: [PlayComponent, HeaderComponent],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: [PlayComponent]
})
export class PlayModule { }
