import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PackRoutingModule } from './pack-routing.module';
import { PackHomeComponent } from './pack-home/pack-home.component';
import { OFormModule, OGridComponent, OTranslateModule, OntimizeWebModule } from 'ontimize-web-ngx';
import { OGalleryModule } from 'ontimize-web-ngx-gallery';
import { PackDetailComponent } from './pack-detail/pack-detail.component';
import { PackNewComponent } from './pack-new/pack-new.component';
import { PackEditComponent } from 'src/app/main/manage-packages/pack-edit/pack-edit.component';


@NgModule({
  declarations: [
    PackHomeComponent,
    PackDetailComponent,
    PackNewComponent,
    PackEditComponent
    
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
