import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import {AdminPanelComponent} from './admin-panel/admin-panel/admin-panel.component';
import {PlayComponent} from './play/play/play.component';
import {ManageComponent} from './manage/manage/manage.component';
import {ReguserComponent} from './regUser/RegUser/reg-user.component';
import {RegKneipenbesitzerComponent} from './regKneipenBesitzer/RegKneipenBesitzer/reg-kneipenbesitzer.component';

const routes: Routes = [
  {path: 'login', component: AdminPanelComponent},
  {path: 'admin-panel', component: AdminPanelComponent},
  {path: '', component: LoginComponent},
  {path: 'logout', component: LoginComponent},
  {path: 'play', component: PlayComponent},
  {path: 'manage', component: ManageComponent},
  {path: 'reguser', component : ReguserComponent},
  {path: 'regkneipenbesitzer', component: RegKneipenbesitzerComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
