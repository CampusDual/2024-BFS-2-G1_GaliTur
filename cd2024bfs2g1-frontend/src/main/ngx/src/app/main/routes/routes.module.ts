import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoutesRoutingModule } from './routes-routing.module';
import { RoutesHomeComponent } from './routes-home/routes-home.component';
import { RoutesDetailComponent } from './routes-detail/routes-detail.component';
import { RoutesNewComponent } from './routes-new/routes-new.component';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { OGalleryModule } from 'ontimize-web-ngx-gallery';
import { AddLandmarkComponent } from './routes-new/add-landmark/add-landmark.component';
import { ViewAllLandmarkComponent } from './routes-new/view-all-landmark/view-all-landmark.component';


@NgModule({
  declarations: [
    RoutesHomeComponent,
    RoutesDetailComponent,
    RoutesNewComponent,
    AddLandmarkComponent,
    ViewAllLandmarkComponent
  ],
  imports: [
    CommonModule,
    RoutesRoutingModule,
    OntimizeWebModule,
    OGalleryModule
  ]
})
export class RoutesModule { }
