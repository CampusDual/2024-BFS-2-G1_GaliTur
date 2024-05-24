import { NgModule } from '@angular/core';
import { ServiceWorkerModule } from '@angular/service-worker';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { APP_CONFIG, ONTIMIZE_MODULES, ONTIMIZE_PROVIDERS, OntimizeWebModule, O_MAT_ERROR_OPTIONS } from 'ontimize-web-ngx';

import { environment } from '../environments/environment';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CONFIG } from './app.config';
import { MainService } from './shared/services/main.service';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OGalleryModule } from 'ontimize-web-ngx-gallery';
import { CustomMessageServiceRoutes } from './shared/services/customMessageRoutes.service';
import { CustomMessageBusinessService } from './shared/services/customMessageBusiness.service';
import { CustomMessageServiceLandmarks } from './shared/services/customMessageLandmarks.service';
import { CustomMessageServicePacks } from './shared/services/customMessagePacks.service';
import { CustomMessageServiceRegister } from './shared/services/customMessageRegisters.service';




// Standard providers...
// Defining custom providers (if needed)...
export const customProviders: any = [
  MainService,
  { provide: O_MAT_ERROR_OPTIONS, useValue: { type: 'lite' } },
  { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } }
];

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    OntimizeWebModule.forRoot(CONFIG),
    OntimizeWebModule,
    AppRoutingModule,
    ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production }),
    OGalleryModule,

  ],
  declarations: [
    AppComponent,
  ],
  bootstrap: [
    AppComponent
  ],
  providers: [
    { provide: APP_CONFIG, useValue: CONFIG },
    ONTIMIZE_PROVIDERS,
    ...customProviders,
    { provide: 'customMessageServiceTypeRoutes', useValue: CustomMessageServiceRoutes },
    { provide: 'customMessageServiceTypeLandmarks', useValue: CustomMessageServiceLandmarks },
    { provide: 'customMessageServiceTypeBusiness', useValue: CustomMessageBusinessService },
    { provide: 'customMessageServiceTypePacks', useValue: CustomMessageServicePacks },
    { provide: 'customMessageServiceTypeRegister', useValue: CustomMessageServiceRegister }
  ],
})
export class AppModule { }
