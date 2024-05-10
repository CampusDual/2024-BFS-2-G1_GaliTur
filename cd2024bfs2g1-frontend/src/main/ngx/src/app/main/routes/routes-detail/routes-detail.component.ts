import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { ViewLandmarkDetailComponent } from './view-landmark-detail/view-landmark-detail.component';
import { LandmarksService } from '../../landmarks.service';

@Component({
  selector: 'app-routes-detail',
  templateUrl: './routes-detail.component.html',
  styleUrls: ['./routes-detail.component.css']
})
export class RoutesDetailComponent implements OnInit{


  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected sanitizer: DomSanitizer,
    protected dialog: MatDialog,
    protected landmarkService: LandmarksService
  ) { }

  ngOnInit(){
  }

  public openDetailLandmark(data: any): void {

    this.landmarkService.getLandmark(data.route_id).subscribe((landmarkData)=> {
      const landmark = []

      if(landmarkData.data.length){
        landmarkData.data.forEach(element => {
          landmarkData.push(element.name)
        });
        data['landmark'] = landmark
      } 

      this.dialog.open(ViewLandmarkDetailComponent, {
        height: '800px',
        width: '1200px',
        data: data,
      });

    })
  }

}

