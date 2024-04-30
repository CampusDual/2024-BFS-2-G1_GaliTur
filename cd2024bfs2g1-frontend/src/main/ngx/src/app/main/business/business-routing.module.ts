import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BusinessNewComponent } from './business-new/business-new.component';


const routes: Routes = [{
  path: '', component: BusinessNewComponent},
  {path: 'new', component: BusinessNewComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BusinessRoutingModule { }
