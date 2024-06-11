import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManageAllPacksComponent } from '../manage-packages/manage-all-packs/manage-all-packs.component';
import { PackEditComponent } from '../manage-packages/pack-edit/pack-edit.component';
import { ManageAllRoutesComponent } from './manage-all-routes/manage-all-routes.component';
import { EditRouteComponent } from './edit-route/edit-route.component';
import { AddLandmarkComponent } from '../routes/routes-new/add-landmark/add-landmark.component';

const routes: Routes = [
  {
    path: '', component: ManageAllRoutesComponent,  data: {
       oPermission: {
         permissionId: 'manageroutes',
         restrictedPermissionsRedirect: '403' 
       }
    }

  },
  {
    path: ':route_id', component: EditRouteComponent,
    data: {
      oPermission: {
        permissionId: 'manageroutesedit',
        restrictedPermissionsRedirect: '403' 
      }
   }
  },
  {
    path: ':route_id/:landmark_id', component: AddLandmarkComponent,
    data: {
      oPermission: {
        permissionId: 'managerlandmarkadd',
        restrictedPermissionsRedirect: '403' 
      }
   }
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoutesManageRoutingModule {


 }
