import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PackRoutingModule } from './pack-routing.module';
import { PackHomeComponent } from './pack-home/pack-home.component';


@NgModule({
  declarations: [
    PackHomeComponent
  ],
  imports: [
    CommonModule,
    PackRoutingModule
  ]
})
export class PackModule { }
