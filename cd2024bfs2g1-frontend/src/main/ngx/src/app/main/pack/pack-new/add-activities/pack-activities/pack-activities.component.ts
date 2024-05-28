import { Component, Injector, ViewChild } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";
import { DomSanitizer } from "@angular/platform-browser";
import { ActivatedRoute } from "@angular/router";
import { AddActivitiesComponent } from "../add-activities.component";
import {
  OComboComponent,
  OSnackBarConfig,
  OTableComponent,
  OntimizeService,
  SnackBarService,
} from "ontimize-web-ngx";

@Component({
  selector: "app-pack-activities",
  templateUrl: "./pack-activities.component.html",
  styleUrls: ["./pack-activities.component.css"],
})
export class PackActivitiesComponent {
  @ViewChild("table", { static: false }) table: OTableComponent;
  @ViewChild("comboBoxDay") comboBoxDay: OComboComponent;

  protected service: OntimizeService;
  protected bsn_service: OntimizeService;
  public arrayDias = [];
  public selectedDay;
  public selectedComboDay;
  selectedBusinessIds: any[] = [];

  constructor(
    protected sanitizer: DomSanitizer,
    private dialogRef: MatDialogRef<PackActivitiesComponent>,
    private activeRoute: ActivatedRoute,
    protected injector: Injector,
    protected snackBarService: SnackBarService
  ) {
    this.service = this.injector.get(OntimizeService);
    this.bsn_service = this.injector.get(OntimizeService);
  }

  ngOnInit(): void {
    // this.table.clearSelection();

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
      this.updateSelectedBusinessIds();
    });

    this.table.onRowDeselected.subscribe(() => {
      this.updateSelectedBusinessIds();
    });
  }
  

  protected configureService() {
    // Configure the service using the configuration defined in the `app.services.config.ts` file
    const conf = this.service.getDefaultServiceConfiguration("packs");
    this.service.configureService(conf);
  }

  protected configureBsnService() {
    // Configure the service using the configuration defined in the `app.services.config.ts` file
    const conf =
      this.bsn_service.getDefaultServiceConfiguration("businessPacks");
    this.bsn_service.configureService(conf);
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

  insertBsnPack() {
    this.configureBsnService();
  
    if (this.selectedBusinessIds.length === 0) {
      const config: OSnackBarConfig = {
        action: "",
        milliseconds: 2000,
        icon: "error",
        iconPosition: "left",
        cssClass: "snackbar",
      };
      this.snackBarService.open("BSNPACK.ERROR", config);
      return;
    }
  
    for (let bp of this.selectedBusinessIds) {
      this.bsn_service
        .insert(
          {
            pck_id: AddActivitiesComponent.packId,
            assigned_date: this.comboBoxDay.getValue(),
            bsn_id: bp
          },
          "businessPack"
        )
        .subscribe(
          (resp) => {
            this.table.clearSelection();
            const config: OSnackBarConfig = {
              action: "",
              milliseconds: 2000,
              icon: "business",
              iconPosition: "left",
              cssClass: "snackbar",
            };
            this.snackBarService.open("BSNPACK.CONFIRMED", config);
          },
          (error) => {
            const config: OSnackBarConfig = {
              action: "",
              milliseconds: 2000,
              icon: "error",
              iconPosition: "left",
              cssClass: "snackbar",
            };
            this.snackBarService.open("BSNPACK.ERROR", config);
          }
        );
    }
  }

  public getImageSrc(base64: any): any {
    return base64
      ? this.sanitizer.bypassSecurityTrustResourceUrl(
          "data:image/*;base64," + base64
        )
      : "./assets/images/no-image-transparent.png";
  }

  truncateName(name: string): string {
    if (name.length > 40) {
      return name.substr(0, 40) + "...";
    } else {
      return name;
    }
  }

  public goBack(): void {
    this.dialogRef.close();
  }

  updateSelectedBusinessIds() {
    const selectedRows = this.table.getSelectedItems();
    this.selectedBusinessIds = selectedRows.map((row) => row.bsn_id);
    console.log(this.selectedBusinessIds); // Para verificar los IDs seleccionados en la consola
  }
}
