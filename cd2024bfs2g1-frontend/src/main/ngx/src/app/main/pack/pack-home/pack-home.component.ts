
import { Component, Injector, ViewChild } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { DomSanitizer } from "@angular/platform-browser";
import { Expression, FilterExpressionUtils, OGridComponent, OntimizeService } from "ontimize-web-ngx";
import { Router } from "@angular/router";


@Component({
  selector: "app-pack-home",
  templateUrl: "./pack-home.component.html",
  styleUrls: ["./pack-home.component.css"],
})
export class PackHomeComponent {
  public showWaitForLongTask = false;
  service: OntimizeService;
  public static page = 0;

    constructor(
    protected injector: Injector,
    private ontimizeService: OntimizeService,
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private router: Router
  ) {
    this.ontimizeService.configureService(
      this.ontimizeService.getDefaultServiceConfiguration("packs")
    );
    this.service = this.injector.get(OntimizeService)
  }
  ngOnInit() {}

  public openDetail(data: any): void {
    PackHomeComponent.page = 1;
    this.router.navigate(['main/packs/' + data.pck_id]);
  }



  public getImageSrc(base64: any): any {
    return base64
      ? this.sanitizer.bypassSecurityTrustResourceUrl(
          "data:image/*;base64," + base64
        )
      : "./assets/images/no-image-transparent.png";
  }


  truncateName(name: string): string {
    if (name.length > 25) {
      return name.substr(0, 25) + "...";
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
  
  filter(values: Array<{ attr: string, value: any }>): Expression {
    let filters: Array<Expression> = [];
    values.forEach(fil => {
        if (fil.value) {
            if (fil.attr === 'pck_name') {
                let keyword = fil.value;
                filters.push(FilterExpressionUtils.buildExpressionLike("pck_name", `%${keyword}%`));
            }
            if (fil.attr === 'pck_date_begin') {
                filters.push(FilterExpressionUtils.buildExpressionMoreEqual("pck_date_begin", fil.value));
            }
            if (fil.attr === 'pck_date_end') {
                filters.push(FilterExpressionUtils.buildExpressionLessEqual("pck_date_end", fil.value));
            }
            if (fil.attr === 'pck_days') {
                let days = Number(fil.value);
                filters.push(FilterExpressionUtils.buildExpressionEquals(
                    `DATE_PART('day', pck_date_end::timestamp - pck_date_begin::timestamp)`,
                    days
                ));
            }
            if (fil.attr === 'pck_price_min') {
                let value: number = Number(fil.value);
                filters.push(FilterExpressionUtils.buildExpressionMoreEqual("pck_price", value));
            }
            if (fil.attr === 'pck_price_max') {
                let value: number = Number(fil.value);
                filters.push(FilterExpressionUtils.buildExpressionLessEqual("pck_price", value));
            }
            if (fil.attr === 'pck_participants') {
                filters.push(FilterExpressionUtils.buildExpressionEquals("pck_participants", fil.value));
            }
            if (fil.attr === 'gui_c_name') {
                filters.push(FilterExpressionUtils.buildExpressionEquals("gui_c_name", fil.value));
            }
        }
    });

    if (filters.length > 0) {
        return filters.reduce((exp1, exp2) => 
            FilterExpressionUtils.buildComplexExpression(exp1, exp2, FilterExpressionUtils.OP_AND)
        );
    } else {
        return null;
    }
}



}
