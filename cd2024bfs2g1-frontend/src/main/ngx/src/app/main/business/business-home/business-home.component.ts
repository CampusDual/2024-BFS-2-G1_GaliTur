import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { BusinessDetailComponent } from '../business-detail/business-detail.component';
import { Subscription } from 'rxjs';
import { OntimizeService } from 'ontimize-web-ngx';
import { ScrollStrategyOptions } from '@angular/cdk/overlay';

@Component({
  selector: 'app-business-home',
  templateUrl: './business-home.component.html',
  styleUrls: ['./business-home.component.css']
})
export class BusinessHomeComponent {

  
  constructor(
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private scrollStrategyOptions: ScrollStrategyOptions
  ) { }

  ngOnInit() {
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64.bytes) : './assets/images/no-image-transparent.png';
  }

  public openDetail(data: any): void {
    this.dialog.open(BusinessDetailComponent, {
      height: '800px',
      width: 'auto',
      scrollStrategy: this.scrollStrategyOptions.reposition(),
      data: data,
    });
  }

  

  /** 
  public showWaitForLongTask = false;
  private subscription: Subscription;

  constructor(
    private ontimizeService: OntimizeService,
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
  ) {
    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration("businesses"));
  }
  ngOnInit() {
  }
  longTaskToBackend() {
    this.showWaitForLongTask = true;
    this.subscription = this.ontimizeService.query(undefined, [], 'longTask').subscribe({
      next: (res: any) => {
        console.log("Long task finished");
      },
      error: (err: any) => this.showWaitForLongTask = false,
      complete: () => this.showWaitForLongTask = false
    });
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64.bytes) : './assets/images/no-image-transparent.png';
  }

  public openDetail(data: any): void {
    this.dialog.open(BusinessDetailComponent, {
      height: '800px',
      width: 'auto',
      data: data,
    });
  }

  */

}
