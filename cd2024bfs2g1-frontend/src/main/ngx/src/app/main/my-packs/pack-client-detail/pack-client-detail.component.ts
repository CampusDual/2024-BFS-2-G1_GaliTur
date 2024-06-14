import { Component, Inject, Injector, OnInit } from '@angular/core';
import { AuthService, DialogService, Expression, FilterExpressionUtils, ODialogConfig, OSnackBarConfig, OTranslateService, OntimizeService, SnackBarService } from 'ontimize-web-ngx';
import { PackHomeComponent } from '../../pack/pack-home/pack-home.component';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { formatDate } from '@angular/common';
import { PackValorationComponent } from '../pack-valoration/pack-valoration.component';

@Component({
  selector: 'app-pack-client-detail',
  templateUrl: './pack-client-detail.component.html',
  styleUrls: ['./pack-client-detail.component.css']
})
export class PackClientDetailComponent implements OnInit{
  private pckId: number
  protected isPackInstance: boolean
  public arrayDias = [];
  public selectedComboDay;

  constructor(
    protected sanitizer: DomSanitizer,
    private router: Router,
    private route: ActivatedRoute,
    private oTranslate: OTranslateService,
    private packDateService: OntimizeService,
    protected dialogService: DialogService,
    protected dialog: MatDialog,
    protected injector: Injector,
    protected bookingService: OntimizeService,
    protected snackBarService: SnackBarService,
    private ontimizeService: OntimizeService,
    @Inject(AuthService) private authService: AuthService,
    @Inject(OntimizeService) protected service: OntimizeService
  ) {
    this.bookingService = this.injector.get(OntimizeService);
  }

  ngOnInit(): void {
    this.isPackInstance = false
    //this.isInstanceOfPack()
    this.getPckId()
      .then(() => this.getDays())
      .catch((err) => console.error('Error during initialization:', err));
  }

  private getPckId(): Promise<void> {
    const confPack = this.packDateService.getDefaultServiceConfiguration('packBookings');
    this.packDateService.configureService(confPack);
    return new Promise((resolve, reject) => {
      this.bookingService.query({ pbk_booking_id: +this.route.snapshot.params['pbk_booking_id'] }, ['PC.pck_id'], 'packBookingDatePack')
        .subscribe({
          next: (result) => {
            this.pckId = result.data[0].pck_id;
            resolve();
          },
          error: (err) => {
            console.error('Error fetching pckId:', err);
            reject(err);
          }
        });
    });
  }

  public formatDate(date:any) : any {
    return new Date(date).toLocaleDateString();
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

  cancelBookPack(event: any, data) {
    const config: ODialogConfig = {
      icon: "warning",
      alertType: "warn",
    };

    if (this.dialogService) {
      this.dialogService.confirm(
        this.oTranslate.get("CANCEL-BOOKING-DIALOG"),
        this.oTranslate.get("CANCEL-BOOKING-DIALOG-B"),
        config
      );
      this.dialogService.dialogRef.afterClosed().subscribe((result) => {
        if (result) {
          this.deleteBooking(data);
          // Actions on confirmation
        } else {
          // Actions on cancellation
        }
      });
    }
  }

  deleteBooking(data) {
    const confBooking = this.ontimizeService.getDefaultServiceConfiguration("packBookings");
    this.bookingService.configureService(confBooking);
    this.bookingService.delete({pbk_booking_id: data.pbk_booking_id},"packBooking").subscribe((resp) => {
        const config: OSnackBarConfig = {
          action: "",
          milliseconds: 2000,
          icon: "booking",
          iconPosition: "left",
          cssClass: "snackbar",
        };
        this.snackBarService.open("CANCEL-BOOKING.CONFIRMED", config);
        this.router.navigate(["main/pack-client"]);
    });

  }

  isLogged() {
    return this.authService.isLoggedIn()
  }

  // private isInstanceOfPack(): void {
  //   const confPack = this.ontimizeService.getDefaultServiceConfiguration('packDates');
  //   this.packDateService.configureService(confPack);
  //   this.packDateService.query({pck_id: + this.route.snapshot.params['pck_id']}, ['pck_id'], 'packDate')
  //     .subscribe((result) => {
  //       if (result.data[0] !== undefined){
  //         this.isPackInstance = true
  //       }
  //     });
  // }

  getCurrentDate(): string {
    return formatDate(new Date(), 'yyyy-MM-dd', 'en');
  }

  diffDays(date1: string, date2: string): number {
    const dt1 = new Date(date1);
    const dt2 = new Date(date2);
    const diffTime = (dt1.getTime() - dt2.getTime());
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    return diffDays;
  }

  getDays() {
    const filter = {
      pck_id: this.route.snapshot.params["pck_id"],
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
  //Metodos para redirect dinamico de business

  openDetailBusiness(data: any): void {
    const currentUrl = this.router.url; // Capturar la URL actual
    const navigationExtras: NavigationExtras = {
      state: { previousUrl: currentUrl },
      relativeTo: this.route // Enviar la URL actual como navigation state
    };
    this.router.navigate(['../../businesses/' + data.bsn_id], navigationExtras);
  }
  //Metodo para redirect dinamico de rutas

  openDetailRoutes(data: any): void {
    const currentUrl = this.router.url; // Capturar la URL actual
    const navigationExtras: NavigationExtras = {
      state: { previousUrl: currentUrl },
      relativeTo: this.route  // Enviar la URL actual como navigation state
    };
    this.router.navigate(['../../routes/' + data.route_id], navigationExtras);
  }

  public getRouteImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl("data:image/*;base64," + base64) : "./assets/images/home-image.jpeg";
  }

  getIconColorClass(difficulty: number): string {
    switch(difficulty) {
        case 1:
            return 'icon-difficulty-1';
        case 2:
            return 'icon-difficulty-2';
        case 3:
            return 'icon-difficulty-3';
        case 4:
            return 'icon-difficulty-4';
    }
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

  truncateName(name: string): string {
    if (name.length > 30) {
        return name.substr(0, 30) + '...';
    } else {
        return name;
    }
  }

  openValoration(stars: Number, data: any): void{
    this.dialog.open(PackValorationComponent, {
      height: '40%',
      width: '40%',
      data: {
        stars: stars,
        data: data
      }
    })
  }

}

