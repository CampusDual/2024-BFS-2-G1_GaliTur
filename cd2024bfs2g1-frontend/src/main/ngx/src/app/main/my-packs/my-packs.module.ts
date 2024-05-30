import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MyPacksRoutingModule } from './my-packs-routing.module';
import { PackClientComponent } from './pack-client/pack-client.component';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { PackClientDetailComponent } from './pack-client-detail/pack-client-detail.component';


@NgModule({
  declarations: [
    PackClientComponent,
    PackClientDetailComponent
  ],
  imports: [
    CommonModule,
    MyPacksRoutingModule,
    OntimizeWebModule
  ]
})
export class MyPacksModule { }
