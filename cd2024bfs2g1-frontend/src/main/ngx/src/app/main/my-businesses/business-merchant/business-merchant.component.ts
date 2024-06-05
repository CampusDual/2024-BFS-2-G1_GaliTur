import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { OGridComponent, OntimizeService } from 'ontimize-web-ngx';
import { Router } from '@angular/router';
import { BusinessHomeComponent } from '../../business/business-home/business-home.component';
 
@Component({
  selector: 'app-business-merchant',
  templateUrl: './business-merchant.component.html',
  styleUrls: ['./business-merchant.component.css']
})
export class BusinessMerchantComponent {
  public showWaitForLongTask = false;
 
  constructor(
    private ontimizeService: OntimizeService,
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private router: Router,
  ) {
    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration("businesses"));
  }
  ngOnInit() {
  }
 
  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }
 
  public openDetail(data: any): void {
    this.router.navigate(['main/business-merchant', +data.bsn_id]);
  }
 
 
}