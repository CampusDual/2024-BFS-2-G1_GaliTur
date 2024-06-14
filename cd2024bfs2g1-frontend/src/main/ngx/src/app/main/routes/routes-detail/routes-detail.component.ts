import { AfterViewInit, Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { ViewLandmarkDetailComponent } from './view-landmark-detail/view-landmark-detail.component';

import { OntimizeService } from 'ontimize-web-ngx';
import { ImageService } from '../../../shared/services/image.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LandmarksService } from 'src/app/shared/services/landmarks.service';

@Component({
  selector: 'app-routes-detail',
  templateUrl: './routes-detail.component.html',
  styleUrls: ['./routes-detail.component.css']
})
export class RoutesDetailComponent implements OnInit{
  galleryOptions: any;


  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private ontimizeService: OntimizeService,
    protected sanitizer: DomSanitizer,
    protected dialog: MatDialog,
    protected landmarkService: LandmarksService,
    private dialogRef: MatDialogRef<RoutesDetailComponent>,
    private router: Router,
    private actRoute: ActivatedRoute
    ) {
    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration("landmarks"));
    this.galleryOptions = [
      {
        image: true,
        height: "250px",
        width: "300px",
        thumbnails: data.galleryImages && data.galleryImages.length > 1 ? true : false,
        imageArrows: data.galleryImages && data.galleryImages.length > 1 ? true : false,
        preview: false
      }
    ]
    this.dialogRef.disableClose = true;

   }



  ngOnInit(){
  }

  public openDetailLandmark(data: any): void {

    const landmarkCoordinates= []

       this.ontimizeService.query({route_id: data.route_id}, ['name', 'l.landmark_id', 'coordinates'], 'landmark' ).subscribe((landmarkData) => {
        data['landmark'] = landmarkData.data
        this.dialog.open(ViewLandmarkDetailComponent, {
          height: '800px',
          width: '1200px',
          data: data,
        });
      })
  }


  coverEyes(): void {
    const leftHand = document.querySelector('.hand.left') as HTMLElement;
    const rightHand = document.querySelector('.hand.right') as HTMLElement;

    leftHand.style.transform = 'rotate(20deg)';
    rightHand.style.transform = 'rotate(-20deg)';
  }

  uncoverEyes(): void {
    const leftHand = document.querySelector('.hand.left') as HTMLElement;
    const rightHand = document.querySelector('.hand.right') as HTMLElement;

    leftHand.style.transform = 'rotate(-45deg)';
    rightHand.style.transform = 'rotate(45deg)';
  }

  public backToHome(data:any): void {
    const previousUrl = history.state && history.state.previousUrl ? history.state.previousUrl : '';
    //Si el usuario viene de packs-detail redigir a la url anterior para que vea el pack en el que estaba
    if (previousUrl.includes('packs')) {
      this.router.navigateByUrl(previousUrl)
      this.dialogRef.close();
     //Si el usuario viene de routes home actuar como el metodo backToHome original
    } else {
      this.dialogRef.close();
      this.router.navigate(['../routes'], { relativeTo: this.actRoute })
    }
}

public convertTime(metros: number):  string {
  let minutos = Math.floor(metros * 0.011);
  if(minutos == 0){
    minutos = 1;
  }

  const horas = Math.floor(minutos / 60);
  const minutosRestantes = minutos % 60;

  if (horas == 0 && minutosRestantes != 0) {
      return `${minutosRestantes}min`;
  } else if (horas != 0 && minutosRestantes == 0) {
      return `${horas}h`;
  } else {
      return `${horas}h ${minutosRestantes}min`;
  }
}

convertDistance(metros: number){
  let kilometros = Math.floor(metros / 1000);
  let metrosRestantes = metros % 1000;

  let metrosRestantesStr = metrosRestantes.toString();
  let metrosRestantesDecimal = metrosRestantesStr.split('.')[0].slice(0, 2);

  metrosRestantes = Number(metrosRestantesDecimal);

  if (kilometros == 0 && metrosRestantes != 0) {
    return `${metrosRestantes}m`;
  } else if (kilometros != 0 && metrosRestantes == 0) {
    return `${kilometros}km`;
  } else if (kilometros != 0 && metrosRestantes != 0) {
    return `${kilometros},${metrosRestantes}km`;
  } else {
    return '0m';
  }
}

public getImageSrc(base64: any): any {
  return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl("data:image/*;base64," + base64) : "./assets/images/home-image.jpeg";
}

getIconColorClass(difficulty: number): string {
  switch(difficulty) {
    case 1:
      return 'icon-difficulty-1';
    case 2:
      return 'icon-difficulty-2';
    case 3:
      return 'icon-difficulty-3';
    case 4:
      return 'icon-difficulty-4';
    }
  }

  getDifficultad(difficulty: number): string {
    switch(difficulty) {
      case 1:
          return 'Fácil';
      case 2:
          return 'Intermedia';
      case 3:
          return 'Difícil';
      case 4:
          return 'Extremo';
    }
  }
}
