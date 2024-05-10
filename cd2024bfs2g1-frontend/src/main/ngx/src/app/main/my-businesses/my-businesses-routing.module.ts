import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BusinessMerchantComponent } from './business-merchant/business-merchant.component';

const routes: Routes = [
  {
    path: '', component: BusinessMerchantComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MyBusinessesRoutingModule { }
