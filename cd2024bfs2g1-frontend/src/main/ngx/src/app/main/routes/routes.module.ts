import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoutesRoutingModule } from './routes-routing.module';
import { RoutesHomeComponent } from './routes-home/routes-home.component';
import { RoutesDetailComponent } from './routes-detail/routes-detail.component';
import { RoutesNewComponent } from './routes-new/routes-new.component';
import { OntimizeWebModule } from 'ontimize-web-ngx';


@NgModule({
  declarations: [
    RoutesHomeComponent,
    RoutesDetailComponent,
    RoutesNewComponent
  ],
  imports: [
    CommonModule,
    RoutesRoutingModule,
    OntimizeWebModule
  ]
})
export class RoutesModule { }
