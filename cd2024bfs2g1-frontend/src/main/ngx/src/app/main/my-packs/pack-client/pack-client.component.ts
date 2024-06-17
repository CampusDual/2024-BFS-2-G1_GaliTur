import { Component, Injector, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { OGridComponent, OntimizeService } from 'ontimize-web-ngx';
import { Router } from '@angular/router';
import { PackHomeComponent } from '../../pack/pack-home/pack-home.component';
import { PackValorationComponent } from '../pack-valoration/pack-valoration.component';

@Component({
  selector: 'app-business-home',
  templateUrl: './pack-client.component.html',
  styleUrls: ['./pack-client.component.css']
})
export class PackClientComponent {
  public showWaitForLongTask = false;
  public service: OntimizeService
  constructor(
    private ontimizeService: OntimizeService,
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private router: Router,
    protected injector: Injector,
  ) {
    this.service = this.injector.get(OntimizeService);
    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration("packs"));
  }
  ngOnInit() {
  }

  public formatDate(date:any) : any {
    return new Date(date).toLocaleDateString();
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/home-image.jpeg';
  }

  public openDetail(data: any): void {
    PackHomeComponent.page = 2;
    this.router.navigate(['main/pack-client', +data.pbk_booking_id]);
  }

  truncateName(name: string): string {
    if (name.length > 25) {
        return name.substr(0, 25) + '...';
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


  getDate (fechaNumber: number): string {
    const tempFecha = new Date(fechaNumber)

    return tempFecha.toLocaleDateString();
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

  getMediaValorations(data): any{
    const conf = this.service.getDefaultServiceConfiguration("packs");
    this.service.configureService(conf);
    const filter = {
      "P.pck_id": data,
    };
    const columns = ["P.pck_id"];
    this.service.query(filter, columns, "packRating").subscribe((resp) => {
      if (resp.code === 0) {
        // resp.data contains the data retrieved from the server
        console.log(resp)
        return resp.rating_avg[0];
      } else {
        alert("Impossible to query data!");
      }
    });
  }

}
