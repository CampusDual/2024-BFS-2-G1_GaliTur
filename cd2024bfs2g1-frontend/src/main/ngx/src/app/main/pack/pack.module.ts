import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PackRoutingModule } from './pack-routing.module';
import { PackHomeComponent } from './pack-home/pack-home.component';
import { OFormModule, OGridComponent, OTranslateModule, OntimizeWebModule } from 'ontimize-web-ngx';
import { OGalleryModule } from 'ontimize-web-ngx-gallery';
import { PackDetailComponent } from './pack-detail/pack-detail.component';
import { PackNewComponent } from './pack-new/pack-new.component';
import { PackActivitiesComponent } from './pack-new/add-activities/pack-activities/pack-activities.component';
import { AddActivitiesComponent } from './pack-new/add-activities/add-activities.component';
import { PackRoutesComponent } from './pack-new/add-activities/pack-routes/pack-routes.component';


@NgModule({
  declarations: [
    PackHomeComponent,
    PackDetailComponent,
    PackNewComponent,
    PackActivitiesComponent,
    AddActivitiesComponent,
    PackRoutesComponent,
  ],
  imports: [
    CommonModule,
    PackRoutingModule,
    OFormModule,
    OGalleryModule,
    OntimizeWebModule,
    OTranslateModule

  ]
})
export class PackModule { }
