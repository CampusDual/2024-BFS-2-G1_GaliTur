import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PackHomeComponent } from './pack-home/pack-home.component';
import { PackNewComponent } from './pack-new/pack-new.component';
import { PackDetailComponent } from './pack-detail/pack-detail.component';
import { AddActivitiesComponent } from './pack-new/add-activities/add-activities.component';

const routes: Routes = [
  {
    path: '', component: PackHomeComponent
  },
  {path: 'new', component: PackNewComponent,
  data: {
    oPermission: {
      permissionId: "PacksNew",
      restrictedPermissionsRedirect: 403
    }
  }
  },
{path: 'new/:pck_id', component: AddActivitiesComponent},
  {path: ':pck_id', component: PackDetailComponent}
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PackRoutingModule { }
