import { Component, OnInit } from '@angular/core';
import { RoutesDetailComponent } from '../routes-detail/routes-detail.component';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-routes-home',
  templateUrl: './routes-home.component.html',
  styleUrls: ['./routes-home.component.css']
})
export class RoutesHomeComponent implements OnInit{
data: any;

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
    this.dialog.open(RoutesDetailComponent, {
      height: '500px',
      width: '800px',
      data: data
    });
  }
}
