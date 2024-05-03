import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BusinessRoutingModule } from './business-routing.module';
import { BusinessNewComponent } from './business-new/business-new.component';
import { OFormModule, OntimizeWebModule } from 'ontimize-web-ngx';
import { OGalleryModule } from 'ontimize-web-ngx-gallery';


@NgModule({
  declarations: [
    BusinessNewComponent
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
