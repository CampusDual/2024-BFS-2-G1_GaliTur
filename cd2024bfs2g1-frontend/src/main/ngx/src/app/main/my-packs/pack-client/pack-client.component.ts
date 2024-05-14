import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { OGridComponent, OntimizeService } from 'ontimize-web-ngx';
import { Router } from '@angular/router';
import moment from 'moment';

@Component({
  selector: 'app-business-home',
  templateUrl: './pack-client.component.html',
  styleUrls: ['./pack-client.component.css']
})
export class PackClientComponent {
  public showWaitForLongTask = false;

  constructor(
    private ontimizeService: OntimizeService,
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private router: Router,
  ) {
    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration("businesses"));
  }
  ngOnInit() {
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }

  public openDetail(data: any): void {
    this.router.navigate(['main/businesses/' + data.bsn_id]);
  }

  truncateName(name: string): string {
    if (name.length > 19) {
        return name.substr(0, 19) + '...';
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



}
