import {Component, Inject, OnInit} from '@angular/core';

import {TaskService} from "../services/TaskService";
import {User} from "../data/User";
import {LoginAndRegistrate} from "../services/LoginAndRegistrate";
import {Router} from "@angular/router";
import {Task} from "../data/Task";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {DialogOverviewExampleDialog} from "../exit-task-window/exit-task-window.component";

@Component({
  selector: 'app-main-window',
  templateUrl: './main-window.component.html',
  styleUrls: ['./main-window.component.css']
})
export class MainWindowComponent implements OnInit {

  tasks: Task[] = [];
  user: User = null;

  constructor(public dialog: MatDialog, private taskService: TaskService, private service: LoginAndRegistrate, private router:Router) {
    this.user =  this.service.getData();
    if(this.user == null || this.user == undefined) {
       this.router.navigateByUrl('/');
     }
  }

  ngOnInit(): void {
    this.taskService.getTask(this.user).subscribe((data:Task[]) => {
      this.tasks = data;
    });
  }

  readMore(task: Task) {
    this.openDialog(task);
  }

  openDialog(task: Task) {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '550px',
      data: task
    });
  }

  finish(task: Task) {
    this.taskService.setDoneTask(task).subscribe(answer => {
      if (answer.toString() == "OK")  {
        this.router.navigateByUrl('/finished');
      }
      else {
        alert("Невозможно завершить задачу! Попробуйте еще.")
      }
    });

  }

  goMain() {
    this.router.navigateByUrl('/main');
  }

  goFree() {
    this.router.navigateByUrl('/free');
  }

  goFinished() {
    this.router.navigateByUrl('/finished');
  }

  goNewtask() {
    this.router.navigateByUrl('/newtask');
  }

}
