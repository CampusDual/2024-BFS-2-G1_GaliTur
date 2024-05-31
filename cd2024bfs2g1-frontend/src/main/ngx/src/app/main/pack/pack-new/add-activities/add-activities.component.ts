import { Component, Injector, ViewChild } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { PackActivitiesComponent } from "./pack-activities/pack-activities.component";
import { ActivatedRoute, Router } from "@angular/router";
import { DialogService, FilterExpressionUtils, ODialogConfig, OSnackBarConfig, OTableComponent, OTranslateService, OntimizeService, SnackBarService } from "ontimize-web-ngx";
import { PackRoutesComponent } from "./pack-routes/pack-routes.component";

@Component({
  selector: "app-add-activities",
  templateUrl: "./add-activities.component.html",
  styleUrls: ["./add-activities.component.css"],
})
export class AddActivitiesComponent {

  @ViewChild("tableBsn", { static: false }) tableBsn: OTableComponent;
  @ViewChild("tableRoutes", { static: false }) tableRoutes: OTableComponent;
  static packId: any;
  protected service: OntimizeService;
  public bsnArray = []


  constructor(
    protected dialog: MatDialog,
    private activeRoute: ActivatedRoute,
    protected injector: Injector,
    private router: Router,
    protected snackBarService: SnackBarService,
    protected dialogService: DialogService,
    private oTranslate: OTranslateService
  ) {
    this.service = this.injector.get(OntimizeService);
  }
  ngOnInit(): void {
    AddActivitiesComponent.packId = this.activeRoute.snapshot.params["pck_id"];

    console.log("Al pack le llego el id: " + AddActivitiesComponent.packId);
    this.getBsn();
    this.getRoute();
  }
  

  deleteBsn() {
    const config: ODialogConfig = {
      icon: "warning",
      alertType: "warn",
    };
    // Obtener las filas seleccionadas
    const selectedItems = this.tableBsn.getSelectedItems();

    if (selectedItems.length === 0) {
      const config: OSnackBarConfig = {
        action: "",
        milliseconds: 2000,
        icon: "error",
        iconPosition: "left",
        cssClass: "snackbar",
      };
      this.snackBarService.open("BSNPACK.ERROR.DELETE", config);
      return;
    }

    // Obtener los IDs de los registros seleccionados
    const idsToDelete = selectedItems.map(item => item.bsn_pack_id);
    if (this.dialogService) {
      this.dialogService.confirm(
        this.oTranslate.get("BSN-DELETE-DIALOG"),
        this.oTranslate.get("BSN-DELETE-DIALOG-B"),
        config
      );
      this.dialogService.dialogRef.afterClosed().subscribe((result) => {
        if (result) {
           // Confirmación de eliminación
   
      const conf = this.service.getDefaultServiceConfiguration("businessPacks");
      this.service.configureService(conf);

      // Eliminar cada registro
      idsToDelete.forEach(id => {
        const filter = { "bsn_pack_id": id };
        this.service.delete(filter, "businessPack").subscribe((resp) => {
          if (resp.code === 0) {
            console.log(`Deleted bsn_pack_id: ${id}`);
            // Actualizar la tabla después de la eliminación
            this.getBsn();
            const config: OSnackBarConfig = {
              action: "",
              milliseconds: 2000,
              icon: "check",
              iconPosition: "left",
              cssClass: "snackbar",
            };
            this.snackBarService.open("BSNPACK.DELETE", config);
          } else {
            alert(`Failed to delete bsn_pack_id: ${id}`);
          }
        });
      });
      };
    })
  }}

  deleteRoute() {
    const config: ODialogConfig = {
      icon: "warning",
      alertType: "warn",
    };
    // Obtener las filas seleccionadas
    const selectedItems = this.tableRoutes.getSelectedItems();

    if (selectedItems.length === 0) {
      const config: OSnackBarConfig = {
        action: "",
        milliseconds: 2000,
        icon: "error",
        iconPosition: "left",
        cssClass: "snackbar",
      };
      this.snackBarService.open("ROUTEPACK.ERROR.DELETE", config);
      return;
    }

    // Obtener los IDs de los registros seleccionados
    const idsToDelete = selectedItems.map(item => item.route_pack_id);
    if (this.dialogService) {
      this.dialogService.confirm(
        this.oTranslate.get("ROUTE-DELETE-DIALOG"),
        this.oTranslate.get("ROUTE-DELETE-DIALOG-B"),
        config
      );
      this.dialogService.dialogRef.afterClosed().subscribe((result) => {
        if (result) {
           // Confirmación de eliminación
   
      const conf = this.service.getDefaultServiceConfiguration("routePacks");
      this.service.configureService(conf);

      // Eliminar cada registro
      idsToDelete.forEach(id => {
        const filter = { "route_pack_id": id };
        this.service.delete(filter, "routePack").subscribe((resp) => {
          if (resp.code === 0) {
            console.log(`Deleted route_pack_id: ${id}`);
            // Actualizar la tabla después de la eliminación
            this.getRoute();
            const config: OSnackBarConfig = {
              action: "",
              milliseconds: 2000,
              icon: "check",
              iconPosition: "left",
              cssClass: "snackbar",
            };
            this.snackBarService.open("ROUTEPACK.DELETE", config);
          } else {
            alert(`Failed to delete route_pack_id: ${id}`);
          }
        });
      });
      };
    })
  }}


  addActivity() {
    
   const diaglogRef = this.dialog.open(PackActivitiesComponent, {
      width: "1200px",
      maxHeight: "800px",
      minHeight: "800px",
      maxWidth: "80vw",
    });
    diaglogRef.afterClosed().subscribe(() => {
      this.getBsn()
    });
  }

  addRoute() {
    
    const diaglogRef = this.dialog.open(PackRoutesComponent, {
       width: "1200px",
       maxHeight: "800px",
       minHeight: "800px",
       maxWidth: "80vw",
     });
     diaglogRef.afterClosed().subscribe(() => {
       this.getRoute()
     });
   }

  getBsn() {
    const conf = this.service.getDefaultServiceConfiguration("businessPacks");
    this.service.configureService(conf);
    const filter = {
      "BP.pck_id": parseInt(AddActivitiesComponent.packId) ,
    };
    const columns = ["bsn_name", "bsn_type","bsn_address","assigned_date","bsn_pack_id"];
    this.service.query(filter, columns, "packBusiness").subscribe((resp) => {
      if (resp.code === 0) {
        // resp.data contains the data retrieved from the server

        this.tableBsn.setDataArray(resp.data);
        this.tableBsn.reloadData()


      } else {
        alert("Impossible to query data!");
      }
    });
  }

  getRoute() {
    const conf = this.service.getDefaultServiceConfiguration("routePacks");
    this.service.configureService(conf);
    const filter = {
      "R.pck_id": parseInt(AddActivitiesComponent.packId) ,
    };
    const columns = ["name", "estimated_duration","difficulty","assigned_date","route_pack_id"];
    this.service.query(filter, columns, "routePack").subscribe((resp) => {
      if (resp.code === 0) {
        // resp.data contains the data retrieved from the server
        
        this.tableRoutes.setDataArray(resp.data);
        this.tableRoutes.reloadData()


      } else {
        alert("Impossible to query data!");
      }
    });
  }

  public goBack(): void {
    this.router.navigate(["main/packs"]);
  }
}
