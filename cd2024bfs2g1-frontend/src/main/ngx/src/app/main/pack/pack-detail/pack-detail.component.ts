import {Component, Inject, Injector, OnInit, ViewChild} from "@angular/core";
import {DomSanitizer} from "@angular/platform-browser";
import {ActivatedRoute, Router} from "@angular/router";
import {
  DialogService,
  ODialogConfig,
  OFormComponent,
  OSnackBarConfig,
  OTableComponent,
  OTranslateService,
  OntimizeService,
  SnackBarService, AuthService, OComboComponent, OGridComponent,
} from "ontimize-web-ngx";
import {PackHomeComponent} from "../pack-home/pack-home.component";
import {UserInfoService} from "../../../shared/services/user-info.service";

@Component({
  selector: "app-pack-detail",
  templateUrl: "./pack-detail.component.html",
  styleUrls: ["./pack-detail.component.css"],
})
export class PackDetailComponent implements OnInit {
  @ViewChild("form") formPack: OFormComponent
  @ViewChild("packDatesForm") packDatesForm: OGridComponent
  @ViewChild("packDateCombo") packDateCombo: OComboComponent
  protected isPackInstance: boolean
  protected availableDates: Set<any> = new Set

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
    @Inject(AuthService) private authService: AuthService,
    @Inject(UserInfoService) private userInfoService: UserInfoService
  ) {
    this.bookingService = this.injector.get(OntimizeService);
  }

  ngOnInit(): void {
    // this.isPackInstance = false
    // this.isInstanceOfPack()
  }

  ngAfterViewInit() {
    this.populateDates()
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

  insertBooking(data) {
    const confBooking = this.bookingService.getDefaultServiceConfiguration("packBookings");
    this.bookingService.configureService(confBooking);
    this.bookingService
      .insert({pd_id: data.pd_id, client_id: this.userInfoService.getUserInfo().usr_id}, "packBooking")
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

  populateDates() {
    const confPack = this.packDateService.getDefaultServiceConfiguration('packDates');
    this.packDateService.configureService(confPack);

    const id = +this.route.snapshot.params["pck_id"]

    this.packDateService.query(
      {pck_id: id},
      ["pd_id", "pd_date_begin", "pd_date_end"],
      "packDate",
      {
        pd_id: 4,
        pd_date_begin: 93,
        pd_date_end: 93
      })
      .subscribe((result) => {
        if (result.data.length) {
          result.data.forEach((date) => {
            date.pd_date_begin = new Date(date.pd_date_begin).toLocaleDateString()
            date.pd_date_end = new Date(date.pd_date_end).toLocaleDateString()
          })
          this.packDateCombo.setDataArray(result.data)
          // console.log(this.availableDates)
        }
      });
  }
}
