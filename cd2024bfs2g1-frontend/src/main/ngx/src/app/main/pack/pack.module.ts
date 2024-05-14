import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PackRoutingModule } from './pack-routing.module';
import { PackHomeComponent } from './pack-home/pack-home.component';
import { OFormModule, OGridComponent, OntimizeWebModule } from 'ontimize-web-ngx';
import { OGalleryModule } from 'ontimize-web-ngx-gallery';


@NgModule({
  declarations: [
    PackHomeComponent
  ],
  imports: [
    CommonModule,
    PackRoutingModule,
    OFormModule,
    OGalleryModule,
    OntimizeWebModule

  ]
})
export class PackModule { }
