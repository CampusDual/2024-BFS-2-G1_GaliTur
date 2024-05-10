import { Component, Inject, Injector, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { DialogService, ODialogConfig, OFormComponent, OTableComponent, OntimizeService } from 'ontimize-web-ngx';
import { BusinessHomeComponent } from '../business-home/business-home.component';

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
    protected dialogService: DialogService,
    protected injector: Injector,
    protected service: OntimizeService
  ) {
    this.service = this.injector.get(OntimizeService);

  }
  

	ngOnInit(): void {
  
      this.configureService();
    
  }
  protected configureService() {
    // Configure the service using the configuration defined in the `app.services.config.ts` file
    const conf = this.service.getDefaultServiceConfiguration('packBookings');
    this.service.configureService(conf);
  }

  insertBooking(data) {
   
    this.service.insert({ "pck_id": data.bsn_id }, "packBooking")
    .subscribe(resp => {
     this.form.reload(true);
    });
    }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }

  public openBusinesses(): void {

    if(BusinessHomeComponent.page==1){
      this.router.navigate(['main/businesses']);
    }else{
      this.router.navigate(['main/business-merchant']);
    }
  }

  bookPack(event: any, data) {

    const config: ODialogConfig = {
      icon: 'warning',
      alertType: 'warn',

    };
    
    if (this.dialogService) {
      this.dialogService.confirm('Confirme reserva del pack', '¿Quiere confirmar la reserva?', config);
      this.dialogService.dialogRef.afterClosed().subscribe( result => {
        if(result) {
          this.insertBooking(data);

          // Actions on confirmation
        } else {
          // Actions on cancellation
        }
      })
    }


  }



 
}
