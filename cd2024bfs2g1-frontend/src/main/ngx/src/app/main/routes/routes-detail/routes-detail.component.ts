import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { ImageService } from '../../image.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ViewLandmarkDetailComponent } from './view-landmark-detail/view-landmark-detail.component';

@Component({
  selector: 'app-routes-detail',
  templateUrl: './routes-detail.component.html',
  styleUrls: ['./routes-detail.component.css']
})
export class RoutesDetailComponent implements OnInit{

  galleryImages: any[] = [];
landmark: any;


  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected sanitizer: DomSanitizer,
    protected dialog: MatDialog
  ) { }

  ngOnInit(){
  }

  public openDetailLandmark(data: any): void {
      this.dialog.open(ViewLandmarkDetailComponent, {
        height: '800px',
        width: '1200px',
        data: data
      });
    
  }

}
