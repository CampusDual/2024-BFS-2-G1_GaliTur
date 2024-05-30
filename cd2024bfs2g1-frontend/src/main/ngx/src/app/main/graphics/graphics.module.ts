import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GraphicsRoutingModule } from './graphics-routing.module';
import { GraphicsHomeComponent } from './graphics-home/graphics-home.component';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { OChartModule } from 'ontimize-web-ngx-charts';


@NgModule({
  declarations: [
    GraphicsHomeComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    GraphicsRoutingModule,
    OChartModule

  ]
})

export class GraphicsModule { }
