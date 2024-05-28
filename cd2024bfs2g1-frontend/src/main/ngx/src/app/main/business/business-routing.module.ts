import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BusinessHomeComponent } from './business-home/business-home.component';
import { BusinessDetailComponent } from './business-detail/business-detail.component';
import { BusinessNewComponent } from './business-new/business-new.component';


const routes: Routes = [
  {
    path: '', component: BusinessHomeComponent
  },
  {
    path: 'new', component: BusinessNewComponent,
    data: {
      oPermission: {
        permissionId: "BusinessNew",
        restrictedPermissionsRedirect: 403
      }
    }
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
