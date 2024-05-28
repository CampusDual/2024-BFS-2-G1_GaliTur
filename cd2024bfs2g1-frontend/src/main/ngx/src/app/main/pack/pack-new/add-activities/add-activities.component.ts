import { Component, ViewChild } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { PackActivitiesComponent } from "./pack-activities/pack-activities.component";
import { ActivatedRoute } from "@angular/router";
import { FilterExpressionUtils, OTableComponent } from "ontimize-web-ngx";

@Component({
  selector: "app-add-activities",
  templateUrl: "./add-activities.component.html",
  styleUrls: ["./add-activities.component.css"],
})
export class AddActivitiesComponent {
  @ViewChild("table", { static: false }) table: OTableComponent;
  static packId: any;

filterExpr = FilterExpressionUtils.buildExpressionLike('pck_id', AddActivitiesComponent.packId);
basicExpr = FilterExpressionUtils.buildBasicExpression(this.filterExpr);




  constructor(
    protected dialog: MatDialog,
    private activeRoute: ActivatedRoute,
    
  ) {
    this.table.queryData(this.basicExpr);
  }
  ngOnInit(): void {
    AddActivitiesComponent.packId = this.activeRoute.snapshot.params["pck_id"];
    console.log("Al pack le llego el id: " + AddActivitiesComponent.packId);
  }

  addActivity() {
    this.dialog.open(PackActivitiesComponent, {
      width: "1200px",
      maxHeight: "600px",
      minHeight: "600px",
      maxWidth: "80vw",
    });
  }
}
