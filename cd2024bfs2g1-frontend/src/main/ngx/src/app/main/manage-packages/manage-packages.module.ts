import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManagePackagesRoutingModule } from './manage-packages-routing.module';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { PackEditComponent } from './pack-edit/pack-edit.component';
import { ManageAllPacksComponent } from './manage-all-packs/manage-all-packs.component';



@NgModule({
  declarations: [
    PackEditComponent,
    ManageAllPacksComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ManagePackagesRoutingModule,
  ]
})
export class ManagePackagesModule { }
