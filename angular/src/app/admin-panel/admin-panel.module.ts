import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import {FormsModule} from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AddQuestionComponent } from './add-question/add-question.component';
import { AddQuestionPackageComponent } from './add-question-package/add-question-package.component';
import { AddPubComponent } from './add-pub/add-pub.component';
import { EditQuestionsComponent } from './edit-questions/edit-questions.component';
import { EditPubComponent } from './edit-pub/edit-pub.component';
import {AppModule} from '../app.module';
import {HeaderComponent} from './header/header.component';


@NgModule({
  declarations: [ AdminPanelComponent, AddQuestionComponent, AddQuestionPackageComponent, AddPubComponent, EditQuestionsComponent, EditPubComponent, HeaderComponent],
  exports: [
    AdminPanelComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AdminPanelModule {}
