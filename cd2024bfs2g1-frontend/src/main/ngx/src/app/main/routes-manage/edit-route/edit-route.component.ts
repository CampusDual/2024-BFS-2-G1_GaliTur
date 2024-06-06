import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { OntimizeService } from 'ontimize-web-ngx';
import { Landmark } from '../../routes/routes-new/view-all-landmark/landmark-model';
import { AfterViewInit, Component } from '@angular/core';

@Component({
  selector: 'app-edit-route',
  templateUrl: './edit-route.component.html',
  styleUrls: ['./edit-route.component.css']
})
export class EditRouteComponent implements AfterViewInit {
onClickLandmark(event: any) {
  this.actualLandkmarkId = event.row.landmark_id
  this.actualCoordinates = event.row.coordinates
  console.log("Estoy tocando la tabla :), y me devuelve: ",event.row.landmark_id)
  console.log("Tocando la tabla me da estas coordenadas: ",event.row.coordinates)
}
  constructor(
    protected dialog: MatDialog,
    private ontimizelandmarkService: OntimizeService,
    private ontimizerouteService: OntimizeService,
    private activeRoute: ActivatedRoute,
    private router: Router
  ) {}

  datosTabla: Landmark[] = [];
  idRutaActual: number;
  nameActualRoute: String;
  actualLandkmarkId = null
  actualCoordinates = null

  ngAfterViewInit(): void {
    this.idRutaActual = +this.getRouteId();
    this.landmarkRouteQuery(this.idRutaActual);
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
