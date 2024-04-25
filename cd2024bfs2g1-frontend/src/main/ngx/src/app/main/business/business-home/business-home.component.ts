import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { BusinessDetailComponent } from '../business-detail/business-detail.component';

@Component({
  selector: 'app-business-home',
  templateUrl: './business-home.component.html',
  styleUrls: ['./business-home.component.css']
})
export class BusinessHomeComponent {
  constructor(
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer
  ) { }

  ngOnInit() {
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64.bytes) : './assets/images/no-image-transparent.png';
  }

  public openDetail(data: any): void {
    this.dialog.open(BusinessDetailComponent, {
      height: '330px',
      width: '520px',
      data: data
    });
  }
}
