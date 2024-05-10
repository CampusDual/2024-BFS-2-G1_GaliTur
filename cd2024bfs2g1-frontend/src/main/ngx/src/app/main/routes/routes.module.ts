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
import { RouteService } from 'src/app/shared/services/route.service';
import { OMapModule } from 'ontimize-web-ngx-map';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    RoutesHomeComponent,
    RoutesDetailComponent,
    RoutesNewComponent,
    AddLandmarkComponent,
    ViewAllLandmarkComponent
  ],
  imports: [
    SharedModule,
    OMapModule,
    CommonModule,
    RoutesRoutingModule,
    OntimizeWebModule,
    OGalleryModule
  ],
  providers:[RouteService]
})
export class RoutesModule { }
