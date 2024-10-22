import { NgModule } from '@angular/core';
import { Router, RouterModule, Routes } from '@angular/router';
import { RoutesHomeComponent } from './routes-home/routes-home.component';
import { RoutesNewComponent } from './routes-new/routes-new.component';
import { RoutesDetailComponent } from './routes-detail/routes-detail.component';
import { AddLandmarkComponent } from './routes-new/add-landmark/add-landmark.component';
import { ViewAllLandmarkComponent } from './routes-new/view-all-landmark/view-all-landmark.component';
import { ViewLandmarkDetailComponent } from './routes-detail/view-landmark-detail/view-landmark-detail.component';


const routes: Routes = [
  { path: '', component: RoutesHomeComponent },
  { path: 'landmark', component: ViewLandmarkDetailComponent},
  { path: 'new', component: RoutesNewComponent,
    data: {
      oPermission: {
        permissionId: "RoutesNew",
        restrictedPermissionsRedirect: 403
      }
    }
  },
  { path: 'new/:route_id', component: ViewAllLandmarkComponent,
    data: {
      oPermission: {
        permissionId: "RoutesNew",
        restrictedPermissionsRedirect: 403
      }
    }
  },
  { path: 'new/:route_id/:landmark_id', component: AddLandmarkComponent,
    data: {
      oPermission: {
        permissionId: "RoutesNew",
        restrictedPermissionsRedirect: 403
      }
    }
  },
  { path: ':route_id', component: RoutesHomeComponent},
 
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoutesRoutingModule {
 
  
 }
