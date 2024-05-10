import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BusinessHomeComponent } from './business-home/business-home.component';
import { BusinessDetailComponent } from './business-detail/business-detail.component';
import { BusinessNewComponent } from './business-new/business-new.component';
import { BusinessMerchantComponent } from './business-merchant/business-merchant.component';


const routes: Routes = [
  {
    path: '', component: BusinessHomeComponent
  },
  {
    path: 'new', component: BusinessNewComponent
  },
  {
    path: 'business-merchant',
    component: BusinessMerchantComponent
  },
  {
    path: 'business-merchant/:bsn_id',
    component: BusinessDetailComponent
  },
  {
    path: ':bsn_id',
    component: BusinessDetailComponent
  },
 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BusinessRoutingModule { }
