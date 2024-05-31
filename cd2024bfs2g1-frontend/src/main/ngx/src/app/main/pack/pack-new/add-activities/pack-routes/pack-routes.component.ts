import { Component, Injector, ViewChild } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";
import { DomSanitizer } from "@angular/platform-browser";
import { ActivatedRoute } from "@angular/router";
import { AddActivitiesComponent } from "../add-activities.component";
import {
  OComboComponent,
  OSnackBarConfig,
  OTableComponent,
  OTranslateService,
  OntimizeService,
  SnackBarService,
} from "ontimize-web-ngx";


@Component({
  selector: 'app-pack-routes',
  templateUrl: './pack-routes.component.html',
  styleUrls: ['./pack-routes.component.css']
})
export class PackRoutesComponent {
  @ViewChild("table", { static: false }) table: OTableComponent;
  @ViewChild("comboBoxDay") comboBoxDay: OComboComponent;

  protected service: OntimizeService;
  protected route_service: OntimizeService;
  public arrayDias = [];
  public selectedDay;
  public selectedComboDay;
  selectedRoutesIds: any[] = [];

  constructor(
    protected sanitizer: DomSanitizer,
    private dialogRef: MatDialogRef<PackRoutesComponent>,
    private activeRoute: ActivatedRoute,
    protected injector: Injector,
    protected snackBarService: SnackBarService,
    private translate: OTranslateService
  ) {
    this.service = this.injector.get(OntimizeService);
    this.route_service = this.injector.get(OntimizeService);
  }

  ngOnInit(): void {
    console.log(
      "Al emergente le llego el id: " + AddActivitiesComponent.packId
    );
    this.configureService();
    this.getDays();
    this.table.clearSelection();
  }

  ngAfterViewInit() {
    // Suscribirse a los eventos de selección y deselección de filas
    this.table.onRowSelected.subscribe(() => {
      this.updateSelectedRoutesIds();
    });

    this.table.onRowDeselected.subscribe(() => {
      this.updateSelectedRoutesIds();
    });
  }
  

  protected configureService() {
    // Configure the service using the configuration defined in the `app.services.config.ts` file
    const conf = this.service.getDefaultServiceConfiguration("packs");
    this.service.configureService(conf);
  }

  protected configureRouteService() {
    // Configure the service using the configuration defined in the `app.services.config.ts` file
    const conf =
      this.route_service.getDefaultServiceConfiguration("routePacks");
    this.route_service.configureService(conf);
  }

  getDays() {
    const filter = {
      pck_id: AddActivitiesComponent.packId,
    };
    const columns = ["pck_name", "pck_days"];
    this.service.query(filter, columns, "packDays").subscribe((resp) => {
      if (resp.code === 0) {
        // resp.data contains the data retrieved from the server

        const array = resp.data;
        const data = array[0];
        const days = data["pck_days"];

        for (let d of days) {
          this.arrayDias.push({ day: d["day"], day_string: d["day_string"] });
        }

        this.selectedComboDay;
      } else {
        alert("Impossible to query data!");
      }
    });
  }

  insertRoutePack() {
    this.configureRouteService();
  
    if (this.selectedRoutesIds.length === 0) {
      const config: OSnackBarConfig = {
        action: "",
        milliseconds: 2000,
        icon: "error",
        iconPosition: "left",
        cssClass: "snackbar",
      };
      this.snackBarService.open("ROUTEPACK.ERROR", config);
      return;
    }
  
    for (let rt of this.selectedRoutesIds) {
      this.route_service
        .insert(
          {
            pck_id: AddActivitiesComponent.packId,
            assigned_date: this.comboBoxDay.getValue(),
            route_id: rt
          },
          "routePack"
        )
        .subscribe(
          (resp) => {
            this.table.clearSelection();
            const config: OSnackBarConfig = {
              action: "",
              milliseconds: 2000,
              icon: "hiking",
              iconPosition: "left",
              cssClass: "snackbar",
            };
            this.snackBarService.open("ROUTEPACK.CONFIRMED", config);
          },
          (error) => {
            const config: OSnackBarConfig = {
              action: "",
              milliseconds: 2000,
              icon: "error",
              iconPosition: "left",
              cssClass: "snackbar",
            };
            this.snackBarService.open("ROUTEPACK.ERROR", config);
          }
        );
    }
  }

  public getImageSrc(base64: any): any {
 
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl("data:image/*;base64," + base64) : "./assets/images/logo-walking.png";
  }

  truncateName(name: string): string {
    if (name.length > 40) {
      return name.substr(0, 40) + "...";
    } else {
      return name;
    }
  }

  public goBack(): void {
    this.table.clearSelection();
    this.dialogRef.close();
  }

  updateSelectedRoutesIds() {
    const selectedRows = this.table.getSelectedItems();
    this.selectedRoutesIds = selectedRows.map((row) => row.route_id);
    console.log(this.selectedRoutesIds); // Para verificar los IDs seleccionados en la consola
  }

  /*Pasar minutos introducidos a h y min*/
  public convertTime(minutos: number):  string {
    if (minutos == null){
      return this.translate.get("N0_ESTIMATED_DURATION")
    }
    const horas = Math.floor(minutos / 60);
    const minutosRestantes = minutos % 60;

    if(horas == 0 && minutosRestantes != 0){
      return minutosRestantes + "min";

    }else if(horas != 0 && minutosRestantes == 0){
      return horas + "h ";

    }
    else {
       return horas + "h " + minutosRestantes + "min";
    }
  
    
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

    /*Mostrar la dificultan en el tooltip*/
    getDifficultad(difficulty: number): string {
      switch(difficulty) {
        case 1:
            return 'Fácil';
        case 2:
            return 'Intermedio';
        case 3:
            return 'Difícil';
        case 4:
            return 'Extremo';
      }
    }

   

}
