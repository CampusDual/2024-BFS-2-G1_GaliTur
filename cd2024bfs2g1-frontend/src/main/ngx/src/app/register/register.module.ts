import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegisterRoutingModule } from './register-routing.module';
import { RegisterClientComponent } from './register-client/register-client.component';
import { RegisterMerchantComponent } from './register-merchant/register-merchant.component';
import {SharedModule} from "../shared/shared.module";
import {OntimizeWebModule} from "ontimize-web-ngx";


@NgModule({
  declarations: [
    RegisterClientComponent,
    RegisterMerchantComponent
  ],
  imports: [
    CommonModule,
    RegisterRoutingModule,
    SharedModule,
    OntimizeWebModule
  ]
})
export class RegisterModule { }
