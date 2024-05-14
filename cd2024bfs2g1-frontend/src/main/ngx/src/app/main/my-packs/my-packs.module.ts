import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MyPacksRoutingModule } from './my-packs-routing.module';
import { PackClientComponent } from './pack-client/pack-client.component';
import { OntimizeWebModule } from 'ontimize-web-ngx';


@NgModule({
  declarations: [
    PackClientComponent
  ],
  imports: [
    CommonModule,
    MyPacksRoutingModule,
    OntimizeWebModule
  ]
})
export class MyPacksModule { }
