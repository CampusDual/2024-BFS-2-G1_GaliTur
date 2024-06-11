import { Component, Injector, ViewChild } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Router } from "@angular/router";
import {
  DialogService,
  NavigationService,
  OListComponent,
  OSnackBarConfig,
  OntimizeService,
  SnackBarService,
} from "ontimize-web-ngx";
import { RouteModel } from "../../routes/route.model";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { ShowPacksConfirmDeleteComponent } from "./show-packs-confirm-delete/show-packs-confirm-delete.component";

@Component({
  selector: "app-manage-all-routes",
  templateUrl: "./manage-all-routes.component.html",
  styleUrls: ["./manage-all-routes.component.css"],
})
export class ManageAllRoutesComponent {

  packsWithThisRoute = [];

  @ViewChild("myList") myList: OListComponent
  constructor(
    protected sanitizer: DomSanitizer,
    private router: Router,
    protected injector: Injector,
    private snackBarService: SnackBarService,
    private dialog: MatDialog,
    private ontimizeRouteService: OntimizeService
  ) {
    this.injector.get(NavigationService).initialize();
  }
  
  protected configureRouteService() {
    const conf =
      this.ontimizeRouteService.getDefaultServiceConfiguration("routes");
    this.ontimizeRouteService.configureService(conf);
  }

  confirmAction(mensaje: string, packs: any, route_id: any) {
    const dialogRef = this.dialog.open(ShowPacksConfirmDeleteComponent, {
      data: { mensaje: mensaje, packs: packs },
    });
    dialogRef.afterClosed().subscribe((result) => {
      console.log("El resultado es ", result);
      if (result) {
        this.deleteRoute(route_id);
      } else return;
    });
  }

  public getImageSrc(base64: any): any {
    const image_code = base64
      ? this.sanitizer.bypassSecurityTrustResourceUrl(
          "data:image/*;base64," + base64
        )
      : "./assets/images/no-image.png";
    return image_code;
  }

  onClicEdit(route_id: any) {
    this.router.navigate(["main", "route-manage", route_id]);
  }

  onClicDelete(route_id: any) {
    this.configureRouteService();
    this.ontimizeRouteService
      .query({ route_id: route_id }, ["p.pck_name"], "searchPacks")
      .subscribe((response) => {
        const packsOfActualRoute: [] = response.data;
        if (packsOfActualRoute.length > 0) {
          this.confirmAction(
            "DELETE_ROUTE_CONFIRM_MESSAGE",
            response.data,
            route_id
          );
        } else {
          this.confirmAction(null,
            null,
            route_id
          );
        }
      });
      
  }

  private deleteRoute(route_id: any) {
    this.ontimizeRouteService
      .delete({ route_id: route_id }, "route")
      .subscribe((deleteResponse) => {
        this.sendDeleteMessage();
        this.myList.reloadPaginatedDataFromStart(true);
      });
  }

  sendDeleteMessage(){
    const config: OSnackBarConfig = {
      action: "",
      milliseconds: 2000,
      icon: "delete",
      iconPosition: "left",
      cssClass: "snackbar",
    };
    this.snackBarService.open("MESSAGES_ROUTE_DELETE_SUCCESS", config);
  }
}
