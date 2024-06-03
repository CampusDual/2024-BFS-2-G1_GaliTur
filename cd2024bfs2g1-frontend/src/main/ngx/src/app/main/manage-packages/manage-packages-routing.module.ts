import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PackEditComponent } from './pack-edit/pack-edit.component';
import { ManageAllPacksComponent } from './manage-all-packs/manage-all-packs.component';
import { PackScheduleComponent } from './pack-edit/pack-schedule/pack-schedule.component';

const routes: Routes = [
  {
    path: '', component: ManageAllPacksComponent,  data: {
      oPermission: {
        permissionId: 'PackEdit',
        restrictedPermissionsRedirect: '403'
      }
    }
  
  },
  {
    path: ':pck_id', component: PackEditComponent, 
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManagePackagesRoutingModule { }
