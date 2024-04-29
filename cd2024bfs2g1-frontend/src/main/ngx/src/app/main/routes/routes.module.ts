import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoutesRoutingModule } from './routes-routing.module';
import { RoutesHomeComponent } from './routes-home/routes-home.component';
import { RoutesDetailComponent } from './routes-detail/routes-detail.component';
import { RoutesNewComponent } from './routes-new/routes-new.component';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { OGalleryModule } from 'ontimize-web-ngx-gallery';
import { AddLandmarkComponent } from './routes-new/add-landmark/add-landmark.component';


@NgModule({
  declarations: [
    RoutesHomeComponent,
    RoutesDetailComponent,
    RoutesNewComponent,
    AddLandmarkComponent
  ],
  imports: [
    CommonModule,
    RoutesRoutingModule,
    OntimizeWebModule,
    OGalleryModule
  ]
})
export class RoutesModule { }
