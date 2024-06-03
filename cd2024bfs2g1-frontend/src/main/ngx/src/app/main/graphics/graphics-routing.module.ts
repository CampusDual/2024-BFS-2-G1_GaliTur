import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GraphicsHomeComponent } from './graphics-home/graphics-home.component';

const routes: Routes = [
  { path: '', component: GraphicsHomeComponent, 
  data: {
    oPermission: {
      permissionId: "graphics",
      restrictedPermissionsRedirect: 403
    }
  }},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GraphicsRoutingModule { }
