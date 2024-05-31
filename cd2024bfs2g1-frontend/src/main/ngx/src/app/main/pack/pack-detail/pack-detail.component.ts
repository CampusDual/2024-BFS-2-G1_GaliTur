import {Component, Inject, Injector, ViewChild} from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import {ActivatedRoute, Router} from "@angular/router";
import {
  DialogService,
  ODialogConfig,
  OFormComponent,
  OSnackBarConfig,
  OTableComponent,
  OTranslateService,
  OntimizeService,
  SnackBarService, AuthService,
} from "ontimize-web-ngx";
import { PackHomeComponent } from "../pack-home/pack-home.component";

@Component({
  selector: "app-pack-detail",
  templateUrl: "./pack-detail.component.html",
  styleUrls: ["./pack-detail.component.css"],
})
export class PackDetailComponent {
  @ViewChild("form") formPack: OFormComponent;
  @ViewChild("form") formPackAndDetails: OFormComponent;

  protected isPackInstance: boolean

  constructor(
    protected sanitizer: DomSanitizer,
    private router: Router,
    private route: ActivatedRoute,
    private oTranslate: OTranslateService,
    private packDateService: OntimizeService,
    protected dialogService: DialogService,
    protected injector: Injector,
    protected bookingService: OntimizeService,
    protected snackBarService: SnackBarService,
    @Inject(AuthService) private authService: AuthService
  ) {
    this.bookingService = this.injector.get(OntimizeService);
  }

  ngOnInit(): void {
    this.isPackInstance = false
    this.configureServices()
    this.isInstanceOfPack()
  }

  public getImageSrc(base64: any): any {
    return base64
      ? this.sanitizer.bypassSecurityTrustResourceUrl(
          "data:image/*;base64," + base64
        )
      : "./assets/images/no-image-transparent.png";
  }

  public openPacks(): void {
    if (PackHomeComponent.page == 1 || !PackHomeComponent.page) {
      this.router.navigate(["main/packs"]);
    } else {
      this.router.navigate(["main/pack-client"]);
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
    this.packDateService.query({pck_id: +this.route.snapshot.params['pck_id']}, ['pck_id'], 'packDate')
      .subscribe((result) => {
        if (result.data[0] !== undefined){
          this.isPackInstance = true
        }
      });
  }

  checkAuthStatus(){
    return !this.authService.isLoggedIn()
  }
}
