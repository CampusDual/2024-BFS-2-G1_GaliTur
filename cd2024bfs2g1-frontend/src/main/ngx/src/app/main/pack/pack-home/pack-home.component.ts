import { Component, ViewChild } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { DomSanitizer } from "@angular/platform-browser";
import { OGridComponent, OntimizeService } from "ontimize-web-ngx";
import { Router } from "@angular/router";
import moment from "moment";

@Component({
  selector: "app-pack-home",
  templateUrl: "./pack-home.component.html",
  styleUrls: ["./pack-home.component.css"],
})
export class PackHomeComponent {
  public showWaitForLongTask = false;

  constructor(
    private ontimizeService: OntimizeService,
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private router: Router
  ) {
    this.ontimizeService.configureService(
      this.ontimizeService.getDefaultServiceConfiguration("packs")
    );
  }
  ngOnInit() {}

  public getImageSrc(base64: any): any {
    return base64
      ? this.sanitizer.bypassSecurityTrustResourceUrl(
          "data:image/*;base64," + base64
        )
      : "./assets/images/no-image-transparent.png";
  }

  public openDetail(data: any): void {
    this.router.navigate(["main/packs/" + data.pck_id]);
  }

  truncateName(name: string): string {
    if (name.length > 16) {
      return name.substr(0, 16) + "...";
    } else {
      return name;
    }
  }

  truncateInfo(name: string): string {
    if (name.length > 10) {
      return name.substr(0, 10) + "...";
    } else {
      return name;
    }
  }

  diferenciaDias(fechaInicio: number, fechaFin: number): number {
    const unDia = 24 * 60 * 60 * 1000; // Número de milisegundos en un día
    const diferencia = Math.abs(fechaFin - fechaInicio);
    return Math.round(diferencia / unDia);
  }

  getDate(fechaNumber: number): string {
    const tempFecha = new Date(fechaNumber);
    return tempFecha.toLocaleDateString();
  }
}
