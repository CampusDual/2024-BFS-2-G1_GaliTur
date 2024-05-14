import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MyBusinessesRoutingModule } from './my-businesses-routing.module';
import { BusinessMerchantComponent } from './business-merchant/business-merchant.component';
import { OntimizeWebModule } from 'ontimize-web-ngx';


@NgModule({
  declarations: [
    BusinessMerchantComponent
  ],
  imports: [
    CommonModule,
    MyBusinessesRoutingModule,
    OntimizeWebModule
    

  ]
})
export class MyBusinessesModule { }
