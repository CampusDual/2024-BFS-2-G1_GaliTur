import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { RoutesDetailComponent } from '../routes-detail/routes-detail.component';
import { ImageService } from '../../../shared/services/image.service';
import { LandmarksService } from '../../../shared/services/landmarks.service';

@Component({
  selector: 'app-routes-home',
  templateUrl: './routes-home.component.html',
  styleUrls: ['./routes-home.component.css']
})
export class RoutesHomeComponent implements OnInit {
data: any;

  constructor(
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private imageService: ImageService,
    //private landmarkService: LandmarksService 
  ) { }

  ngOnInit() {
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:images/*;base64,' + base64.bytes) : './assets/images/no-image.png';
  }

  public openDetail(data: any): void {
    this.imageService.getImage(data.route_id).subscribe((imageData)=> {
      const images = []
      
      if(imageData.data.length){
        imageData.data.forEach(element => {
        images.push({ medium: "data:image/jpeg;base64," + element.img_code})
        });
        data['galleryImages'] = images
      }

      //this.landmarkService.getLandmark(data.route_id).subscribe

      this.dialog.open(RoutesDetailComponent, {
        height: '500px',
        width: '1000px',
        data: data
      });
    })
  }


}
