import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegisterRoutingModule } from './register-routing.module';
import { RegisterHomeComponent } from './register-home/register-home.component';
import { RegisterClientComponent } from './register-client/register-client.component';
import { RegisterMerchantComponent } from './register-merchant/register-merchant.component';
import { RegisterManagerComponent } from './register-manager/register-manager.component';


@NgModule({
  declarations: [
    RegisterHomeComponent,
    RegisterClientComponent,
    RegisterMerchantComponent,
    RegisterManagerComponent
  ],
  imports: [
    CommonModule,
    RegisterRoutingModule
  ]
})
export class RegisterModule { }
