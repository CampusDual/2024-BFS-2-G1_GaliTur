import { Component, Injector, ViewChild } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { PackActivitiesComponent } from "./pack-activities/pack-activities.component";
import { ActivatedRoute } from "@angular/router";
import { FilterExpressionUtils, OTableComponent, OntimizeService } from "ontimize-web-ngx";
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
    protected injector: Injector
  ) {
    this.service = this.injector.get(OntimizeService);
  }
  ngOnInit(): void {
    AddActivitiesComponent.packId = this.activeRoute.snapshot.params["pck_id"];

    console.log("Al pack le llego el id: " + AddActivitiesComponent.packId);
    this.getBsn();
    this.getRoute();
  }


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
}