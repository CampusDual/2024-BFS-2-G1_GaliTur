import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PackClientComponent } from './pack-client/pack-client.component';

const routes: Routes = [
  {
    path: '', component: PackClientComponent,  data: {
      oPermission: {
        permissionId: 'my-clients-permissions',
        restrictedPermissionsRedirect: '403'
      }
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MyPacksRoutingModule { }
