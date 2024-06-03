import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PublicComponentComponent } from './public-component/public-component.component';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { SharedModule } from '../shared/shared.module';
import { PublicRoutingModule } from './public-routing.module';



@NgModule({
  declarations: [
    PublicComponentComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    SharedModule,
    PublicRoutingModule
  ]
})
export class PublicModule { }
