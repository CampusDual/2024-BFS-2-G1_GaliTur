import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MyBusinessesRoutingModule } from './my-businesses-routing.module';
import { BusinessMerchantComponent } from './business-merchant/business-merchant.component';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { BusinessMerchantDetailComponent } from './business-merchant-detail/business-merchant-detail.component';
import { BusinessEditComponent } from './business-edit/business-edit.component';


@NgModule({
  declarations: [
    BusinessMerchantComponent,
    BusinessMerchantDetailComponent,
    BusinessEditComponent
  ],
  imports: [
    CommonModule,
    MyBusinessesRoutingModule,
    OntimizeWebModule
  ]
})
export class MyBusinessesModule { }
