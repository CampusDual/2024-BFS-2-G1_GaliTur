import { AfterViewInit, Component} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { RoutesDetailComponent } from '../routes-detail/routes-detail.component';
import { ImageService } from 'src/app/shared/services/image.service';
import { OntimizeService } from 'ontimize-web-ngx';
import { ActivatedRoute} from '@angular/router';


@Component({
  selector: 'app-routes-home',
  templateUrl: './routes-home.component.html',
  styleUrls: ['./routes-home.component.css']
})
export class RoutesHomeComponent {
galleryOptions: any;

  constructor(
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private imageService: ImageService,
    private ontimizerouteService: OntimizeService,
    private activeRoute: ActivatedRoute
  ) {

  }

  ngAfterViewInit(): void {
    const idRutaActual = +this.getRouteId();
    const confRoute =
      this.ontimizerouteService.getDefaultServiceConfiguration("routes");
    this.ontimizerouteService.configureService(confRoute);
    if(!isNaN(idRutaActual)){
      this.ontimizerouteService
      .query(
        { route_id: idRutaActual },
        ["route_id","name", "description", "estimated_duration", "difficulty"],
        "route"
      )
      .subscribe((response) => {
        this.openDetail(response.data[0]);
      });
    }
  }

  getRouteId(): number {
    return +this.activeRoute.snapshot.params["route_id"];
  }

  /*Recoger img de BD*/
  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl("data:image/*;base64," + base64) : "./assets/images/logo-walking.png";
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
        height: '700px',
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
