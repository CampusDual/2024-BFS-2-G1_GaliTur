import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PackHomeComponent } from './pack-home/pack-home.component';
import { PackDetailComponent } from './pack-detail/pack-detail.component';

const routes: Routes = [
  {
    path: '', component: PackHomeComponent
  },
  {path: ':pck_id', component: PackDetailComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PackRoutingModule { }
