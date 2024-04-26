import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BusinessRoutingModule } from './business-routing.module';
import { BusinessHomeComponent } from './business-home/business-home.component';
import { BusinessDetailComponent } from './business-detail/business-detail.component';
import { OFormModule, OntimizeWebModule } from 'ontimize-web-ngx';
import { OGalleryModule } from 'ontimize-web-ngx-gallery';


@NgModule({
  declarations: [
    BusinessHomeComponent,
    BusinessDetailComponent
  ],
  imports: [
    CommonModule,
    BusinessRoutingModule,
    OntimizeWebModule,
    OFormModule, 
    OGalleryModule
    
  ]
})
export class BusinessModule { }
