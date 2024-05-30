import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { OFormComponent, OTableComponent, OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-business-detail',
  templateUrl: './business-detail.component.html',
  styleUrls: ['./business-detail.component.css']
})
export class BusinessDetailComponent {

  @ViewChild('accountCustomerTable') accountTable: OTableComponent;
  @ViewChild('form') form: OFormComponent;

  constructor(
    protected sanitizer: DomSanitizer,
    private router: Router,
  ) {
  }

	ngOnInit(): void {
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }

  public openBusinesses(): void {
    this.router.navigate(['main/businesses']);
  }


  isLastCity(cityKey: string, cities: string): boolean {
    const cityArray = cities.split(',');
    return cityArray[cityArray.length - 1].trim() === cityKey.trim();
  }

  isLastLanguage(languageKey: string, languages: string): boolean {
    const languageArray = languages.split(',');
    return languageArray[languageArray.length - 1].trim() === languageKey.trim();
  }
}