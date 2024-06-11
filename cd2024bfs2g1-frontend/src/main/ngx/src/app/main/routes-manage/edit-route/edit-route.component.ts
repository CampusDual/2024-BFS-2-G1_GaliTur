import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { OSnackBarConfig, OTableComponent, OTranslateService, OntimizeService, SnackBarService } from 'ontimize-web-ngx';
import { Landmark } from '../../routes/routes-new/view-all-landmark/landmark-model';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { OMapComponent } from 'ontimize-web-ngx-map';

@Component({
  selector: 'app-edit-route',
  templateUrl: './edit-route.component.html',
  styleUrls: ['./edit-route.component.css']
})
export class EditRouteComponent{



@ViewChild('oMap') oMap : OMapComponent
@ViewChild('landmarkTable') landmarkTable : OTableComponent

  constructor(
    private snackBarService: SnackBarService,
    private translate: OTranslateService
  ) {}


  datosTabla: Landmark[] = [];
  idRutaActual: number;
  nameActualRoute: String;
  actualLandkmarkId = null
  actualCoordinates : string = '42.940599,-7.120727'
  mostrarMapa = false


  onDeleteLandMark() {
    this.sendDeleteMessage()
  }

  async onClickLandmark(event: any) {

    this.actualLandkmarkId = null
    this.actualCoordinates = null
    this.actualLandkmarkId = event.row.landmark_id
    this.actualCoordinates = event.row.coordinates
    if(this.actualCoordinates!=null) {
      const coordinatesArrayAux = this.actualCoordinates.split(',')
      this.oMap.addMarker("1",coordinatesArrayAux[0],coordinatesArrayAux[1],{},true,false,true,"1")
      await this.delay(300);
      this.oMap.getMapService().setZoom(12)
    }else alert("Lo sentimos, el punto de interes no cuenta con coordenadas")
  }
  onClickMap() {
    this.mostrarMapa = !this.mostrarMapa
  }
  async delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  sendDeleteMessage(){
    const config: OSnackBarConfig = {
      action: "",
      milliseconds: 2000,
      icon: "delete",
      iconPosition: "left",
      cssClass: "snackbar",
    };
    this.snackBarService.open("LANDMARKDELETECONFIRMED", config);
  }

  public difficultyArray = [{
    difficultyCode: 1,
    difficultyText: this.translate.get("EASY")
  }, {
    difficultyCode: 2,
    difficultyText:  this.translate.get("INTERMEDIATE")
  }, {
    difficultyCode: 3,
    difficultyText:  this.translate.get("HARD")
  }, {
    difficultyCode: 4,
    difficultyText:  this.translate.get("EXTREME")
  }]
}