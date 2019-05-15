import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Task} from "../data/Task";
import {LoginAndRegistrate} from "../services/LoginAndRegistrate";
import {User} from "../data/User";
import {TaskService} from "../services/TaskService";
import {DialogOverviewExampleDialog} from "../exit-task-window/exit-task-window.component";
import {MatDialog} from "@angular/material";

@Component({
  selector: 'app-svobod-task-window',
  templateUrl: './svobod-task-window.component.html',
  styleUrls: ['./svobod-task-window.component.css']
})
export class SvobodTaskWindowComponent implements OnInit {

  tasks: Task[] = [];
  user: User = null;

  constructor(public dialog: MatDialog, private taskService: TaskService, private service: LoginAndRegistrate, private router:Router) {
    this.user =  this.service.getData();
    if(this.user == null || this.user == undefined) {
      this.router.navigateByUrl('/');
    }
  }

  ngOnInit(): void {
    this.taskService.getTasks().subscribe((data:Task[]) => {
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

  select(task: Task) {
    this.taskService.selectTask(task, this.user).subscribe(answer => {
      if (answer.toString() == "OK")  {
        this.router.navigateByUrl('/main');
      }
      else {
        alert("Невозможно назначить вам эту задачу! Попробуйте еще раз.")
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
