import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { RoutesDetailComponent } from '../routes-detail/routes-detail.component';
import { ImageService } from 'src/app/shared/services/image.service';


@Component({
  selector: 'app-routes-home',
  templateUrl: './routes-home.component.html',
  styleUrls: ['./routes-home.component.css']
})
export class RoutesHomeComponent implements OnInit {
galleryOptions: any;

  constructor(
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private imageService: ImageService,
  ) { }

  ngOnInit() {
  }

  public getImageSrc(base64: any): any {

    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl("data:image/*;base64," + base64): "./assets/images/no-image.png";
  }


  /*Abrir detalle de la ruta*/
  public openDetail(data: any): void {
    this.imageService.getImage(data.route_id).subscribe((imageData)=> {
      const images = []

      if(imageData.data.length){
        imageData.data.forEach(element => {
        images.push({ medium: "data:image/jpeg;base64," + element.img_code})
        });
        data['galleryImages'] = images
      }
      this.dialog.open(RoutesDetailComponent, {
        height: '600px',
        width: '1200px',
        data: data
      });
    })
  }

  /*Pasar minutos introducidos a h y min*/
  public convertTime(minutos: number):  string {

    const horas = Math.floor(minutos / 60);
    const minutosRestantes = minutos % 60;

    if(horas == 0 && minutosRestantes != 0){
      return minutosRestantes + "min";

    }else if(horas != 0 && minutosRestantes == 0){
      return horas + "h ";

    }else{
       return horas + "h " + minutosRestantes + "min";
    }
  }

  /*Modificar color de  hojas según su dificultad*/
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

  /*Mostrar la dificultan en el tooltip*/
  getDifficultad(difficulty: number): string {
    switch(difficulty) {
      case 1:
          return 'Dificultad: Fácil';
      case 2:
          return 'Dificultad: Intermedio';
      case 3:
          return 'Dificultad: Difícil';
      case 4:
          return 'Dificultad: Extremo';
    }
  }
}
