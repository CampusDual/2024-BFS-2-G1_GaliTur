import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DialogService} from "ontimize-web-ngx";

@Component({
  selector: 'app-register-home',
  templateUrl: './register-home.component.html',
  styleUrls: ['./register-home.component.css']
})
export class RegisterHomeComponent {

  constructor(
    protected dialogService: DialogService) {
  }

  showInfo($event: any) {

  }
}
