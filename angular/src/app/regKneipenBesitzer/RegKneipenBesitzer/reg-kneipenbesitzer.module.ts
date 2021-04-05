import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RegKneipenbesitzerComponent} from './reg-kneipenbesitzer.component';

import {ReactiveFormsModule} from '@angular/forms';
import {HeaderComponent} from '../header/header.component';

@NgModule({
  declarations: [RegKneipenbesitzerComponent, HeaderComponent],
  imports: [
    CommonModule, ReactiveFormsModule
  ]
})
export class RegKneipenbesitzerModule { }
