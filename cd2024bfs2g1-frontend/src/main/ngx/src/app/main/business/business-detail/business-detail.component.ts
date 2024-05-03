import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { OFormComponent, OTableComponent, OntimizeService } from 'ontimize-web-ngx';
import { GalleryImage, GalleryOptions } from 'ontimize-web-ngx-gallery';


@Component({
  selector: 'app-business-detail',
  templateUrl: './business-detail.component.html',
  styleUrls: ['./business-detail.component.css']
})
export class BusinessDetailComponent {

  @ViewChild('accountCustomerTable') accountTable: OTableComponent;
  @ViewChild('form') form: OFormComponent;

  constructor(
    protected sanitizer: DomSanitizer
  ) {
  }

  public galleryOptions: GalleryOptions[];
  public galleryImages: GalleryImage[];


	ngOnInit(): void {
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }
}
