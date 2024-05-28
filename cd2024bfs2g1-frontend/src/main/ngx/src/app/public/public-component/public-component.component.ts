import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PermissionsService } from 'ontimize-web-ngx';


@Component({
  selector: 'app-public-component',
  templateUrl: './public-component.component.html',
  styleUrls: ['./public-component.component.css']
})
export class PublicComponentComponent {
  hasPermission = false;
  constructor(
    private permissionService: PermissionsService,
    private router: Router
  ) {
    this.permissionService.getUserPermissionsAsPromise().then(x => this.hasPermission = true);
  }
  navigateLogin() {
    this.router.navigate(['../../login'])
  }

  navigateRegister() {
    this.router.navigate(['../../register'])
  }
}