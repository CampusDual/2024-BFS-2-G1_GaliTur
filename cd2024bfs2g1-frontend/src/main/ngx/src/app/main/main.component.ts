import {Component, Inject, OnInit} from '@angular/core';
import {AuthService} from "ontimize-web-ngx";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(@Inject(AuthService) private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
  }
  checkAuthStatus(){
    return !this.authService.isLoggedIn()
  }

  navigateLogin() {
    this.router.navigate(['../../login'])
  }

  navigateRegister() {
    this.router.navigate(['../../register'])
  }
}
