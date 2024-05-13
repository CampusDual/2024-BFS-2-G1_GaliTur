import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PackNewComponent } from './pack-new/pack-new.component';

const routes: Routes = [
  {path: 'new', component: PackNewComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PackRoutingModule { }
