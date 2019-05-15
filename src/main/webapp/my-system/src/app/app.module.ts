import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WindowHomeComponentComponent } from './window-home-component/window-home-component.component';
import {RouterModule, Routes} from "@angular/router";
import { RegistrationHomeComponent } from './registration-home/registration-home.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import {  ReactiveFormsModule } from '@angular/forms';
import { MainWindowComponent } from './main-window/main-window.component';
import { SvobodTaskWindowComponent } from './svobod-task-window/svobod-task-window.component';
import {DialogOverviewExampleDialog, ExitTaskWindowComponent} from './exit-task-window/exit-task-window.component';
import { NewTaskWindowComponent } from './new-task-window/new-task-window.component';
import {TaskService} from "./services/TaskService";
import {LoginAndRegistrate} from "./services/LoginAndRegistrate";
import {DemoMaterialModule} from "./material-module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatNativeDateModule} from "@angular/material";


const appRoutes: Routes = [
  { path: '', component:  WindowHomeComponentComponent },
  { path: 'registration', component: RegistrationHomeComponent },
  { path: 'main', component: MainWindowComponent },
  { path: 'free', component: SvobodTaskWindowComponent },
  { path: 'newtask', component:  NewTaskWindowComponent },
  { path: 'finished', component: ExitTaskWindowComponent }
];



@NgModule({
  declarations: [
    AppComponent,
    WindowHomeComponentComponent,
    RegistrationHomeComponent,
    MainWindowComponent,
    SvobodTaskWindowComponent,
    ExitTaskWindowComponent,
    NewTaskWindowComponent,
    DialogOverviewExampleDialog
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    DemoMaterialModule,
    BrowserModule, FormsModule,
    AppRoutingModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatNativeDateModule,
  ],
  entryComponents: [ExitTaskWindowComponent, DialogOverviewExampleDialog],
  providers: [LoginAndRegistrate, TaskService],
  bootstrap: [AppComponent]
})
export class AppModule {


}
