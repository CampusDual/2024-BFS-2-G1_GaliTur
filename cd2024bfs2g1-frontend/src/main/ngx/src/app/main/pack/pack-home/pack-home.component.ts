
import { Component, Injector, ViewChild, TemplateRef } from "@angular/core";
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { DomSanitizer } from "@angular/platform-browser";
import { Expression, FilterExpressionUtils, OGridComponent, OntimizeService } from "ontimize-web-ngx";
import { Router } from "@angular/router";

@Component({
  selector: "app-pack-home",
  templateUrl: "./pack-home.component.html",
  styleUrls: ["./pack-home.component.css"],
})
export class PackHomeComponent {

  @ViewChild('filterDialogTemplate', { static: true }) filterDialogTemplate: TemplateRef<any>;
  @ViewChild('packGrid', { static: true }) packGrid: OGridComponent;

  public showWaitForLongTask = false;
  service: OntimizeService;
  public static page = 0;
  private dialogRef: MatDialogRef<any>;

  constructor(
    protected injector: Injector,
    private ontimizeService: OntimizeService,
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private router: Router,
  ) {
    this.ontimizeService.configureService(
      this.ontimizeService.getDefaultServiceConfiguration("packs")
    );
    this.service = this.injector.get(OntimizeService);
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
    return name.length > 25 ? name.substr(0, 25) + "..." : name;
  }

  truncateInfo(name: string): string {
    return name.length > 10 ? name.substr(0, 10) + "..." : name;
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
              let dateBegin = fil.value;
              filters.push(FilterExpressionUtils.buildExpressionMoreEqual("pck_date_begin", new Date(dateBegin).toISOString().slice(0, 10)));
            }
            if (fil.attr === 'pck_date_end') {
                let dateEnd = fil.value;
                filters.push(FilterExpressionUtils.buildExpressionLessEqual("pck_date_end", new Date(dateEnd).toISOString().slice(0, 10)));
            }
            if (fil.attr === 'pck_days') {
              let days = Number(fil.value);
              filters.push(FilterExpressionUtils.buildExpressionEquals(
                  `DATE_PART('day', DATE_TRUNC('day', pck_date_end) - DATE_TRUNC('day', pck_date_begin))`,
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

  showFilter(evt: any) {
    this.dialogRef = this.dialog.open(this.filterDialogTemplate, {
      width: '50%',
      height: '50%',
    });
  }

  applyFilter() {
    const filterBuilder = this.filterDialogTemplate['filterBuilder'];
    const filters = filterBuilder.getExpression();
    this.packGrid.queryData(filters);
    this.dialogRef.close();
  }

  closeDialog() {
    this.dialogRef.close();
  }
}
