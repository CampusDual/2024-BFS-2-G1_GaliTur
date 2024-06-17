import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OGalleryModule } from 'ontimize-web-ngx-gallery';
import { BusinessDetailComponent } from './business-detail/business-detail.component';
import { BusinessHomeComponent } from './business-home/business-home.component';
import { BusinessRoutingModule } from './business-routing.module';
import { BusinessNewComponent } from './business-new/business-new.component';
import { OFormModule, OntimizeWebModule } from 'ontimize-web-ngx';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [
    BusinessHomeComponent,
    BusinessDetailComponent,
    BusinessNewComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    BusinessRoutingModule,
    OFormModule,
    OGalleryModule,
    SharedModule,

  ]
})
export class BusinessModule { }
