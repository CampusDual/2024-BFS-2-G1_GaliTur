import { Component, Injector, ViewChild } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { PackActivitiesComponent } from "./pack-activities/pack-activities.component";
import { ActivatedRoute } from "@angular/router";
import { FilterExpressionUtils, OTableComponent, OntimizeService } from "ontimize-web-ngx";

@Component({
  selector: "app-add-activities",
  templateUrl: "./add-activities.component.html",
  styleUrls: ["./add-activities.component.css"],
})
export class AddActivitiesComponent {
  @ViewChild("table", { static: false }) table: OTableComponent;
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

        this.table.setDataArray(resp.data);
        this.table.reloadData()


      } else {
        alert("Impossible to query data!");
      }
    });
  }
}
