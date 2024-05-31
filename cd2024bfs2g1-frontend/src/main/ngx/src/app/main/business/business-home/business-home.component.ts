import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { BusinessDetailComponent } from '../business-detail/business-detail.component';
import { AuthService, OGridComponent, OPermissions, OntimizeService, Util } from 'ontimize-web-ngx';
import { Router } from '@angular/router';

@Component({
  selector: 'app-business-home',
  templateUrl: './business-home.component.html',
  styleUrls: ['./business-home.component.css']
})
export class BusinessHomeComponent {
  public showWaitForLongTask = false;
  form: any;

  constructor(
    private ontimizeService: OntimizeService,
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private router: Router,
    private authService: AuthService
  ) {
    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration("businesses"));
  }
  ngOnInit() {
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }

  public openDetail(data: any): void {
    this.router.navigate(['businesses/' + data.bsn_id]);
  }

  truncateName(name: string): string {
    if (name.length > 30) {
        return name.substr(0, 30) + '...';
    } else {
        return name;
    }
  }
  parsePermissions(attr: string): boolean {
    
    // if oattr in form, it can have permissions
    if (!this.form || !Util.isDefined(this.form.oattr)) {
      return;
    }
      const permissions: OPermissions = this.form.getFormActionsPermissions(attr)
      
      if (!Util.isDefined(permissions)) {
        return true
      }
      return permissions.visible
  }
}
