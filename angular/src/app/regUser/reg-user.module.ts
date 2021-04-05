import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReguserComponent } from './RegUser/reg-user.component';
import {HeaderComponent} from './header/header.component';
import {ReactiveFormsModule} from '@angular/forms';



@NgModule({
  declarations: [ReguserComponent, HeaderComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ]
})
export class ReguserModule { }
