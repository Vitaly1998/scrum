import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginAndRegistrate} from "../services/LoginAndRegistrate";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration-home',
  templateUrl: './registration-home.component.html',
  styleUrls: ['./registration-home.component.css',
  './vendor/bootstrap/css/bootstrap.min.css',
  './fonts/font-awesome-4.7.0/css/font-awesome.min.css',
  './fonts/Linearicons-Free-v1.0.0/icon-font.min.css',
  './vendor/animate/animate.css',
  './vendor/css-hamburgers/hamburgers.min.css',
  'vendor/animsition/css/animsition.min.css',
  'vendor/select2/select2.min.css',
  'vendor/daterangepicker/daterangepicker.css']
})
export class RegistrationHomeComponent implements OnInit {

  regForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private registrationService: LoginAndRegistrate,  private router:Router) { }


  ngOnInit() {
    this.regForm = this.formBuilder.group({
      name: ['',Validators.required],
      surname: ['',Validators.required],
      role: ['',  Validators.compose([
      Validators.required,
      Validators.pattern(new RegExp("developer|designer|analytic|product_owner|tester|DEVELOPER|DESIGNER|ANALYTIC|PRODUCT_OWNER|TESTER"))
      ])],
      username: ['', Validators.compose([
        Validators.required,
        Validators.minLength(6)
      ])],
      password: ['',  Validators.compose([
        Validators.required,
        Validators.minLength(6)
      ])]
    });
  }

  get reg() { return this.regForm.controls; }

  getData() {
    this.submitted = true;
    if (this.regForm.invalid) {
      return;
    }
    else {
      this.registrationService.regUser(this.regForm.value).subscribe(answer => {
        if (answer.toString() == "OK")  {
            this.registrationService.setData(this.regForm.value);
            this.router.navigateByUrl('/main');
        }
        else {
          alert("Пользователь с таким логином уже существует!")
        }
      });
    }
    this.submitted = false;
  }



}
