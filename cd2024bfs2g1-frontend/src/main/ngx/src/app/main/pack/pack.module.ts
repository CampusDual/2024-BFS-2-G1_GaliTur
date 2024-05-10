import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PackRoutingModule } from './pack-routing.module';
import { PackHomeComponent } from './pack-home/pack-home.component';
import { PackDetailComponent } from './pack-detail/pack-detail.component';


@NgModule({
  declarations: [
    PackHomeComponent,
    PackDetailComponent
  ],
  imports: [
    CommonModule,
    PackRoutingModule
  ]
})
export class PackModule { }
