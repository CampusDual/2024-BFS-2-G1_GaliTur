import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PackHomeComponent } from './pack-home/pack-home.component';

const routes: Routes = [
  {
    path: '', component: PackHomeComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PackRoutingModule { }
