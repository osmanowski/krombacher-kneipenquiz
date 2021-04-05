import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import { AppComponent } from './app.component';
import {AdminPanelModule} from './admin-panel/admin-panel.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import {HttpInterceptorService} from './http-interceptor.service';
import { LogoutComponent } from './logout/logout.component';
import {PlayModule} from './play/play.module';
import {ManageModule} from './manage/manage.module';
import {ReguserModule} from './regUser/reg-user.module';
import {RegKneipenbesitzerModule} from './regKneipenBesitzer/RegKneipenBesitzer/reg-kneipenbesitzer.module';
import {ReguserComponent} from './regUser/RegUser/reg-user.component';
import {RegKneipenbesitzerComponent} from './regKneipenBesitzer/RegKneipenBesitzer/reg-kneipenbesitzer.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    // ReguserComponent,
    // RegKneipenbesitzerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AdminPanelModule,
    HttpClientModule,
    FormsModule,
    PlayModule,
    ManageModule,
    ReguserModule,
    RegKneipenbesitzerModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
