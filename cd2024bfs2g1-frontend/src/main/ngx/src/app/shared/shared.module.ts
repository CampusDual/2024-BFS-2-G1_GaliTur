import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { FilterComponent } from './components/filters/filters.component';
import { HomeToolbarComponent } from './components/home-toolbar/home-toolbar.component';
import { OMapModule } from 'ontimize-web-ngx-map';

@NgModule({
  imports: [
    OntimizeWebModule,
    OMapModule
  ],
  declarations: [
    FilterComponent,
    HomeToolbarComponent
  ],
  exports: [
    CommonModule,
    FilterComponent,
    HomeToolbarComponent,
    OMapModule
  ]
})
export class SharedModule { }
