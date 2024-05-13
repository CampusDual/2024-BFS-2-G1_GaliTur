import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PackNewComponent } from './pack-new/pack-new.component';
import { PackDetailComponent } from './pack-detail/pack-detail.component';
import { PackHomeComponent } from './pack-home/pack-home.component';

const routes: Routes = [
  {path:'', component: PackHomeComponent},
  {path: 'new', component: PackNewComponent},
  {path: ':pck_id', component: PackDetailComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PackRoutingModule { }
