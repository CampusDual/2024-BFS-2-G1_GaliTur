import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BusinessHomeComponent } from './business-home/business-home.component';
import { BusinessDetailComponent } from './business-detail/business-detail.component';

const routes: Routes = [
  {
    path: '',
    component: BusinessHomeComponent
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
