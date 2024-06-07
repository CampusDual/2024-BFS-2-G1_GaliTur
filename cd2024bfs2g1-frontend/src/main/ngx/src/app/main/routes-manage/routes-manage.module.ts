import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoutesManageRoutingModule } from './routes-manage-routing.module';
import { ManageAllRoutesComponent } from './manage-all-routes/manage-all-routes.component';
import { EditRouteComponent } from './edit-route/edit-route.component';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { RoutesModule } from '../routes/routes.module';
import { OMapModule } from 'ontimize-web-ngx-map';
import { ShowPacksConfirmDeleteComponent } from './manage-all-routes/show-packs-confirm-delete/show-packs-confirm-delete.component';


@NgModule({
  declarations: [
    ManageAllRoutesComponent,
    EditRouteComponent,
    ShowPacksConfirmDeleteComponent
  ],
  imports: [
    CommonModule,
    RoutesManageRoutingModule,
    OntimizeWebModule,
    RoutesModule,
    OMapModule

  ]
})
export class RoutesManageModule { }
