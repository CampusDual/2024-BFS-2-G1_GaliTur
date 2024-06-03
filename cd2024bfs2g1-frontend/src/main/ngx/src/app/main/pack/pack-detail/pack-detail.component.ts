import {Component, Inject, Injector, ViewChild} from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import {ActivatedRoute, NavigationExtras, Router} from "@angular/router";
import {
  DialogService,
  ODialogConfig,
  OFormComponent,
  OSnackBarConfig,
  OTableComponent,
  OTranslateService,
  OntimizeService,
  SnackBarService, AuthService,
  Expression,
  FilterExpressionUtils,
  OPermissions,
  Util,
} from "ontimize-web-ngx";
import { PackHomeComponent } from "../pack-home/pack-home.component";

@Component({
  selector: "app-pack-detail",
  templateUrl: "./pack-detail.component.html",
  styleUrls: ["./pack-detail.component.css"],
})
export class PackDetailComponent {
  @ViewChild("form") formPack: OFormComponent;

  protected isPackInstance: boolean

  public arrayDias = [];
  public selectedDay;
  public selectedComboDay;

  constructor(
    protected sanitizer: DomSanitizer,
    private router: Router,
    private activeRoute: ActivatedRoute,
    private oTranslate: OTranslateService,
    private packDateService: OntimizeService,
    protected dialogService: DialogService,
    protected injector: Injector,
    protected bookingService: OntimizeService,
    protected snackBarService: SnackBarService,
    @Inject(AuthService) private authService: AuthService,
    @Inject(OntimizeService) protected service: OntimizeService
  ) {
    this.bookingService = this.injector.get(OntimizeService);
  }

  ngOnInit(): void {
    this.isPackInstance = false
    this.configureServices()
    this.isInstanceOfPack()
    this.getDays()
  }

  public getImageSrc(base64: any): any {
    return base64
      ? this.sanitizer.bypassSecurityTrustResourceUrl(
          "data:image/*;base64," + base64
        )
      : "./assets/images/no-image-transparent.png";
  }

  public openPacks(): void {
  
      this.router.navigate(["../"], {relativeTo: this.activeRoute});
  
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

  bookPack(event: any, data) {
    const config: ODialogConfig = {
      icon: "warning",
      alertType: "warn",
    };

    if (this.dialogService) {
      this.dialogService.confirm(
        this.oTranslate.get("BOOKING-DIALOG"),
        this.oTranslate.get("BOOKING-DIALOG-B"),
        config
      );
      this.dialogService.dialogRef.afterClosed().subscribe((result) => {
        if (result) {
          this.insertBooking(data);
          // Actions on confirmation
        } else {
          // Actions on cancellation
        }
      });
    }
  }

  protected configureServices() {
    // Configure the service using the configuration defined in the `app.services.config.ts` file
    const confBooking = this.bookingService.getDefaultServiceConfiguration("packBookings");
    this.bookingService.configureService(confBooking);
    const confPack = this.packDateService.getDefaultServiceConfiguration('packDates');
    this.packDateService.configureService(confPack);
  }

  insertBooking(data) {
    this.bookingService
      .insert({ pck_id: data.pck_id }, "packBooking")
      .subscribe((resp) => {
        //TODO: this.form.reload(true);

        const config: OSnackBarConfig = {
          action: "",
          milliseconds: 2000,
          icon: "booking",
          iconPosition: "left",
          cssClass: "snackbar",
        };
        this.snackBarService.open("BOOKING.CONFIRMED", config);
      });
  }

  isLogged() {
    return this.authService.isLoggedIn()
  }

  private isInstanceOfPack(): void {
    this.packDateService.query({pck_id: +this.activeRoute.snapshot.params['pck_id']}, ['pck_id'], 'packDate')
      .subscribe((result) => {
        if (result.data[0] !== undefined){
          this.isPackInstance = true
        }
      });
  }

  //Metodos para redirect dinamico de business
  openDetailBusiness(data: any): void {
    const currentUrl = this.router.url; // Capturar la URL actual
    const navigationExtras: NavigationExtras = {
      state: { previousUrl: currentUrl },
      relativeTo: this.activeRoute // Enviar la URL actual como navigation state
    };
    this.router.navigate(['../../businesses/' + data.bsn_id], navigationExtras);
  }


  //Metodo para redirect dinamico de rutas
  openDetailRoutes(data: any): void {
    const currentUrl = this.router.url; // Capturar la URL actual
    const navigationExtras: NavigationExtras = {
      state: { previousUrl: currentUrl }, 
      relativeTo: this.activeRoute  // Enviar la URL actual como navigation state
    };
    this.router.navigate(['../../routes/' + data.route_id], navigationExtras);
  }



  public getRouteImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl("data:image/*;base64," + base64) : "./assets/images/logo-walking.png";
  }

  getDifficultad(difficulty: number): string {
    switch(difficulty) {
      case 1:
        return 'Fácil';
      case 2:
        return 'Intermedio';
      case 3:
        return 'Difícil';
      case 4:
        return 'Extremo';
    }
  }

    //FILTROS
    @ViewChild("daySelectorForm") protected daySelectorForm: OFormComponent;
    createFilter(values: Array<{ attr: string, value: any }>): Expression {
      let filters: Array<Expression> = [];

      values.forEach(fil => {
        if (fil.value) {
          if (fil.attr === 'assigned_date') {
            let value: number = Number(fil.value);
            filters.push(FilterExpressionUtils.buildExpressionEquals("assigned_date", value));
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

    public array: Object[] = [{
      key: 1,
      value: '1'
    }, {
      key: 2,
      value: '2'
    }, {
      key: 3,
      value: '3'
    }, {
      key: 4,
      value: '4'
    }];

    getDays() {
      const filter = {
        pck_id: this.activeRoute.snapshot.params["pck_id"],
      };
      const confPack = this.packDateService.getDefaultServiceConfiguration('packs');
      this.packDateService.configureService(confPack);
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


    returnArray(): any[] {
      return this.array;
    }

    @ViewChild('gridBusinessesOfPack', { static: true }) gridBusinessesOfPack: any;
    @ViewChild('gridRoutesOfPack', { static: true }) gridRoutesOfPack: any;

    applyFilter(value: any): void {
      const filter = { assigned_date: value };

      this.gridBusinessesOfPack.queryData(filter);
      this.gridRoutesOfPack.queryData(filter);
    }

  checkAuthStatus(){
    return !this.authService.isLoggedIn()
  }
  parsePermissions(attr: string): boolean {

    // if oattr in form, it can have permissions
    if (!this.formPack || !Util.isDefined(this.formPack.oattr)) {
      return;
    }
      const permissions: OPermissions = this.formPack.getFormComponentPermissions(attr)

      if (!Util.isDefined(permissions)) {
        return true
      }
      return permissions.visible
  }
}
