import {Component, Inject, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Task} from "../data/Task";
import {LoginAndRegistrate} from "../services/LoginAndRegistrate";
import {User} from "../data/User";
import {TaskService} from "../services/TaskService";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
@Component({
  selector: 'app-exit-task-window',
  templateUrl: './exit-task-window.component.html',
  styleUrls: ['./exit-task-window.component.css']
})
export class ExitTaskWindowComponent implements OnInit {

  tasks: Task[] = [];
  user: User = null;

  constructor(public dialog: MatDialog, private taskService: TaskService, private service: LoginAndRegistrate, private router:Router) {
    this.user =  this.service.getData();
    if(this.user == null || this.user == undefined) {
      this.router.navigateByUrl('/');
    }
  }

  ngOnInit(): void {
    this.taskService.getFinishedTasks(this.user).subscribe((data:Task[]) => {
      this.tasks = data;
    });
  }

  readMore(task: Task) {
    this.openDialog(task);
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

  openDialog(task: Task) {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '550px',
      data: task
    });
  }

}


@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'dialog-overview-example-dialog.html',
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Task) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
