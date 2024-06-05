import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BusinessMerchantComponent } from './business-merchant/business-merchant.component';
import { BusinessMerchantDetailComponent } from './business-merchant-detail/business-merchant-detail.component';

const routes: Routes = [
  {
    path: '', component: BusinessMerchantComponent
  },
  {path: ':bsn_id', component: BusinessMerchantDetailComponent}
];
 
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MyBusinessesRoutingModule { }