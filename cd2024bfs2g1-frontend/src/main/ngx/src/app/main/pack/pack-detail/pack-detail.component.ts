import { Component, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { OFormComponent, OTableComponent, OTranslateModule } from 'ontimize-web-ngx';
import { PackHomeComponent } from '../pack-home/pack-home.component';

@Component({
  selector: 'app-pack-detail',
  templateUrl: './pack-detail.component.html',
  styleUrls: ['./pack-detail.component.css']
})
export class PackDetailComponent {
  @ViewChild('accountCustomerTable') accountTable: OTableComponent;
  @ViewChild('form') form: OFormComponent;

  constructor(
    protected sanitizer: DomSanitizer,
    private router: Router,
    private oTranslate: OTranslateModule
  ) {
  }

	ngOnInit(): void {
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }

  public openPacks(): void {
    if(PackHomeComponent.page==1){
      this.router.navigate(['main/packs']);
    }else{
      this.router.navigate(['main/pack-client']);
    }
  }

  diferenciaDias(fechaInicio: number, fechaFin: number): number {
    const unDia = 24 * 60 * 60 * 1000; // Número de milisegundos en un día
    const diferencia = Math.abs(fechaFin - fechaInicio);
    return Math.round(diferencia / unDia);
  }


  getDate (fechaNumber: number): string {
    const tempFecha = new Date(fechaNumber)
    const day = tempFecha.getDay()
    const month = tempFecha.getMonth()
    const year = tempFecha.getFullYear()
    return `${day}/${month}/${year}`;
  }

}
