
import { Component, Injector, ViewChild, TemplateRef } from "@angular/core";
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { DomSanitizer } from "@angular/platform-browser";
import { Expression, FilterExpressionUtils, OCurrencyInputComponent, OFormComponent, OFormValue, OGridComponent, OntimizeService } from "ontimize-web-ngx";
import { ActivatedRoute, NavigationExtras, Router } from "@angular/router";

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
    private route: ActivatedRoute
  ) {
    this.ontimizeService.configureService(
      this.ontimizeService.getDefaultServiceConfiguration("packs")
    );
    this.service = this.injector.get(OntimizeService);
  }

  ngOnInit() {}



    //Metodo para redirect dinamico de rutas
    openDetail(data: any): void {
      PackHomeComponent.page = 1;
      const currentUrl = this.router.url; // Capturar la URL actual
      const navigationExtras: NavigationExtras = {
        state: { previousUrl: currentUrl },
        relativeTo: this.route  // Enviar la URL actual como navigation state
      };
      this.router.navigate(['/packs/' + data.pck_id], navigationExtras);
    }

  public getImageSrc(base64: any): any {
    return base64
      ? this.sanitizer.bypassSecurityTrustResourceUrl(
          "data:image/*;base64," + base64
        )
      : "./assets/images/home-image.jpeg";
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

  //FILTROS
  @ViewChild("filterForm") protected filterForm: OFormComponent;

  createFilter(values: Array<{ attr: string, value: any }>): Expression {
    let filters: Array<Expression> = [];

    values.forEach(fil => {
      if (fil.value) {
        if (fil.attr === 'pck_name') {
          let keyword = fil.value;
          filters.push(FilterExpressionUtils.buildExpressionLike("pck_name", `%${keyword}%`));
        }
        if (fil.attr === 'pck_days') {
          filters.push(FilterExpressionUtils.buildExpressionEquals("pck_days", fil.value));
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
        if (fil.attr === 'pd_date_begin') {
          /* Si nos fijamos en el error que daba en el back, funcion buildExpressionEquals genera esta consulta "AND  (UPPER(pd_date_begin) = UPPER( '2024-06-02 00:00:00.000' ))"
              el problema radica en que esto esta preparado para strings, de ahí la función UPPER, y cuando le aplica el UPPER al timestamp "pd_date_begin" da error. 
              Error del back: "ERROR: function upper(timestamp without time zone) does not exist"
              Para solucionar esto en vez de hacer la solicitud directamente a la fecha en la BBDD hacemos una pequeña subconsulta que convierte la fecha 
              a un "string" con el formato de la fecha sin hora, aparte de pasarle la fecha que selecciona el usuario con el mismo formato y en tipo string.
          */

          let consulta = "(SELECT TO_CHAR(pd_date_begin , 'YYYY-MM-DD'))";
          filters.push(FilterExpressionUtils.buildExpressionEquals(consulta, fil.value));

        }
        
      }
    });

    if (filters.length > 0) {
      return filters.reduce((exp1, exp2) => 
        FilterExpressionUtils.buildComplexExpression(exp1, exp2, 'AND')
      );
    } else {
      return null;
    }
  }

  clearFilter () {
    const fieldsToClear = ['pck_name', 'pck_days', 'pck_price_min', 'pck_price_max', 'pck_participants', 'gui_c_name', 'pd_date_begin'];
    this.filterForm.clearFieldValues(fieldsToClear);  
  }

  @ViewChild("minPrice") protected minPrice: OCurrencyInputComponent;
  @ViewChild("maxPrice") protected maxPrice: OCurrencyInputComponent;

  onCurrencyInputChange() {
    const minValue = this.minPrice.getValue();
    const maxValue = this.maxPrice.getValue();

    if (minValue === "" || minValue === "0.00") {
      this.minPrice.clearValue();
    }
    if (maxValue === "" || maxValue === "0.00") {
      this.maxPrice.clearValue();
    }
  }
}
