import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-routes-detail',
  templateUrl: './routes-detail.component.html',
  styleUrls: ['./routes-detail.component.css']
})
export class RoutesDetailComponent implements OnInit {

  galleryImages: string[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected sanitizer: DomSanitizer
  ) { }

  ngOnInit(): void  {
    this.cargarImagenes();
  }

  /*public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64.bytes) : './assets/images/no-image.png';
  }*/

  public cargarImagenes(): void {
    this.galleryImages = [
      'assets/images/login_bg.png',
      'assets/images/no-image.png',
      'assets/images/photo3.jpg'
    ];
  }  
}
