import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../data/User";
import {LoginAndRegistrate} from "../services/LoginAndRegistrate";
import {TaskService} from "../services/TaskService";
import {Task} from "../data/Task";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-new-task-window',
  templateUrl: './new-task-window.component.html',
  styleUrls: ['./new-task-window.component.css']
})
export class NewTaskWindowComponent implements OnInit {

  taskForm: FormGroup;
  submitted = false;

  user: User = null;

  constructor(private formBuilder: FormBuilder, private router: Router,  private service: LoginAndRegistrate, private taskService: TaskService)  {
    this.user =  this.service.getData();
    if(this.user == null || this.user == undefined) {
      this.router.navigateByUrl('/');
    }
  }


  get reg() { return this.taskForm.controls; }

  ngOnInit() {
    this.taskForm = this.formBuilder.group({
      name: ['',Validators.required],
      purpose: ['',Validators.required],
      priority: ['',  Validators.compose([
        Validators.required,
        Validators.pattern(new RegExp("low|medium|high|critical|LOW|MEDIUM|HIGH|CRITICAL"))
      ])],
      user_new: ['', Validators.compose([
      ])],
      project: ['',  Validators.compose([
        Validators.required
      ])]
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

  getData() {
    this.submitted = true;
    if (this.taskForm.invalid) {
      return;
    }
    else {
      this.taskService.newTask(this.taskForm.value, this.user).subscribe(answer => {
        if (answer.toString() == "OK")  {
          alert("Задача создана!");
          this.taskForm.setValue({name:"", purpose:"", priority:"", user_new:"", project:""});
        }
        else {
          alert("Ошибка! Проверьте введенную информацию!");
        }
      });
      console.log(this.taskForm.value);
    }
    this.submitted = false;
  }
}
