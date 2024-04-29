import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-routes-detail',
  templateUrl: './routes-detail.component.html',
  styleUrls: ['./routes-detail.component.css']
})
export class RoutesDetailComponent implements OnInit {

  galleryImages: any[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected sanitizer: DomSanitizer
  ) { }

  ngOnInit(): void  {
    this.cargarImagenes();
  }

  public cargarImagenes(): void {
    this.galleryImages = [
      {
        medium: 'assets/images/login_bg.png'},
      {
        medium: 'assets/images/no-image.png'}
    ];
  }
}
