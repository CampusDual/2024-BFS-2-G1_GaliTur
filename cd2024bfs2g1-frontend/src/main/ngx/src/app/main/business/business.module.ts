import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BusinessRoutingModule } from './business-routing.module';
import { BusinessNewComponent } from './business-new/business-new.component';
import { BusinessDetailComponent } from './business-detail/business-detail.component';
import { OFormModule, OntimizeWebModule } from 'ontimize-web-ngx';
import { OGalleryModule } from 'ontimize-web-ngx-gallery';


@NgModule({
  declarations: [
    BusinessNewComponent,
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
