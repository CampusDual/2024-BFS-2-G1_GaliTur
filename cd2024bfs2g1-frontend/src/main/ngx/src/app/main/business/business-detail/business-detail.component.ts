import { Component, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ActivatedRoute, Router } from '@angular/router';
import { OFormComponent, OTableComponent, OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-business-detail',
  templateUrl: './business-detail.component.html',
  styleUrls: ['./business-detail.component.css']
})
export class BusinessDetailComponent implements OnInit {
  @ViewChild('accountCustomerTable') accountTable: OTableComponent;
  @ViewChild('form') form: OFormComponent;

  constructor(
    protected sanitizer: DomSanitizer,
    private router: Router,
    private actRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void { }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }

  public openBusinesses(): void {
    const previousUrl = history.state && history.state.previousUrl ? history.state.previousUrl : '';
    //Si el usuario viene de packs-detail redigir a la url anterior para que vea el pack en el que estaba
    if (previousUrl.includes('packs')) {
      this.router.navigateByUrl(previousUrl);
     //Si el usuario viene de business home redigir de vuelta business home
    } else if (previousUrl.includes('/main/businesses')) {
      this.router.navigate(['/main/businesses']);
      //En caso de que entre introduciendo una URL no se contemplada en los casos anteriores redirigir a landing page
    } else {
      this.router.navigate(['../'],{ relativeTo: this.actRoute });
    }
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
