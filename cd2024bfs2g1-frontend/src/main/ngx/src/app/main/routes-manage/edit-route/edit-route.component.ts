import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { OntimizeService } from 'ontimize-web-ngx';
import { Landmark } from '../../routes/routes-new/view-all-landmark/landmark-model';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { OMapComponent } from 'ontimize-web-ngx-map';

@Component({
  selector: 'app-edit-route',
  templateUrl: './edit-route.component.html',
  styleUrls: ['./edit-route.component.css']
})
export class EditRouteComponent implements OnInit {

@ViewChild('oMap') oMap : OMapComponent
  constructor(
    private ontimizelandmarkService: OntimizeService,
    private activeRoute: ActivatedRoute,
  ) {}
  ngOnInit(): void {
    this.idRutaActual = +this.getRouteId();
    this.landmarkRouteQuery(this.idRutaActual);
  }

  datosTabla: Landmark[] = [];
  idRutaActual: number;
  nameActualRoute: String;
  actualLandkmarkId = null
  actualCoordinates : string = null
  mostrarMapa = false

  onClickLandmark(event: any) {
    this.actualLandkmarkId = null
    this.actualCoordinates = null
    this.actualLandkmarkId = event.row.landmark_id
    this.actualCoordinates = event.row.coordinates
    if(this.actualCoordinates!=null) {
      const coordinatesArrayAux = this.actualCoordinates.split(',')
      this.oMap.addMarker("1",coordinatesArrayAux[0],coordinatesArrayAux[1],{},true,false,true,"1")
    }else alert("Lo sentimos, el punto de interes no cuenta con coordenadas")
  }
  onClickMap() {
    this.mostrarMapa = !this.mostrarMapa
  }
  getRouteId(): number {
    return +this.activeRoute.snapshot.params["route_id"];
  }


  landmarkRouteQuery(id: any): void {
    const confLandmark =
    this.ontimizelandmarkService.getDefaultServiceConfiguration("landmarks");
    this.ontimizelandmarkService.configureService(confLandmark);
    this.ontimizelandmarkService
      .query(
        { route_id: id },
        ["l.landmark_id","l.name","l.coordinates","l.description"],
        "landmark"
      )
      .subscribe((response) => {
        console.log("Datos de landmarks: ", response)
        this.datosTabla.push(...response.data);
      });
  }
}
