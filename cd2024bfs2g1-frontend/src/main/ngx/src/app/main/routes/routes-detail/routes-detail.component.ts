import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { ImageService } from '../../image.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-routes-detail',
  templateUrl: './routes-detail.component.html',
  styleUrls: ['./routes-detail.component.css']
})
export class RoutesDetailComponent {

  galleryImages: any[] = [];


  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected sanitizer: DomSanitizer,
  ) { }

}