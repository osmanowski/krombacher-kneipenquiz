import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManageComponent } from './manage/manage.component';
import {HeaderComponent} from './header/header.component';
import {ReactiveFormsModule} from '@angular/forms';



@NgModule({
    declarations: [ManageComponent, HeaderComponent],
    imports: [
        CommonModule,
        ReactiveFormsModule
    ]
})
export class ManageModule { }
