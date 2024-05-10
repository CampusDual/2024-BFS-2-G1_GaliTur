import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PackHomeComponent } from './pack-home/pack-home.component';
import { PackClientComponent } from './pack-client/pack-client.component';

const routes: Routes = [
  {
    path: '', component: PackHomeComponent,
    
  },
  {
    path: 'pack-client',
    component: PackClientComponent
  }
// {
//   path: 'pack-client/:pck_id',
//   component: PackClientComponent  //aqui seria PackDetailComponent
// }
  
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PackRoutingModule { }
