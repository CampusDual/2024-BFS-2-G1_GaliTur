import { Component, Inject, Injector, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { OFormComponent, OSnackBarConfig, OTableComponent, OntimizeService, SnackBarService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-business-merchant-detail',
  templateUrl: './business-merchant-detail.component.html',
  styleUrls: ['./business-merchant-detail.component.css']
})
export class BusinessMerchantDetailComponent implements OnInit {

  @ViewChild('accountCustomerTable') accountTable: OTableComponent;
  @ViewChild('form') form: OFormComponent;
  @Inject(OntimizeService) protected service: OntimizeService
  constructor(
    protected sanitizer: DomSanitizer,
    private router: Router,
    private actRoute: ActivatedRoute,
    protected injector: Injector,
    protected bsnService: OntimizeService,
    protected snackBarService: SnackBarService,
  ) {
    this.bsnService = this.injector.get(OntimizeService);

  }

  ngOnInit(): void { }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }

  public openBusinesses(): void {
    const previousUrl = history.state && history.state.previousUrl ? history.state.previousUrl : '';
    this.router.navigateByUrl('/main/business-merchant');
    //Si el usuario viene de business-detail redigir a la url anterior para que vea el pack en el que estaba
  
}


  isLastCity(cityKey: string, cities: string): boolean {
    const cityArray = cities.split(',');
    return cityArray[cityArray.length - 1].trim() === cityKey.trim();
  }

  isLastLanguage(languageKey: string, languages: string): boolean {
    const languageArray = languages.split(',');
    return languageArray[languageArray.length - 1].trim() === languageKey.trim();
  }

  deleteBsn(data) {

    
      const confBusiness = this.bsnService.getDefaultServiceConfiguration("businesses");
      this.bsnService.configureService(confBusiness);
      this.bsnService
        .delete({bsn_id: data[0].bsn_id},"businessDownDate")
        .subscribe((resp) => {
          //TODO: this.form.reload(true);
  
          const config: OSnackBarConfig = {
            action: "",
            milliseconds: 2000,
            icon: "check",
            iconPosition: "left",
            cssClass: "snackbar",
          };
          this.snackBarService.open("BSN.DELETED", config);
          this.router.navigate(["..", "main", "business-merchant"])
        });
    }


    
}
