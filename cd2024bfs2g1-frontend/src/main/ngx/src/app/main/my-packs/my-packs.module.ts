import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MyPacksRoutingModule } from './my-packs-routing.module';
import { PackClientComponent } from './pack-client/pack-client.component';


@NgModule({
  declarations: [
    PackClientComponent
  ],
  imports: [
    CommonModule,
    MyPacksRoutingModule
  ]
})
export class MyPacksModule { }
