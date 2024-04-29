import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoutesHomeComponent } from './routes-home/routes-home.component';
import { RoutesNewComponent } from './routes-new/routes-new.component';
import { RoutesDetailComponent } from './routes-detail/routes-detail.component';
import { AddLandmarkComponent } from './routes-new/add-landmark/add-landmark.component';

const routes: Routes = [
  { path: '', component: RoutesHomeComponent },
  { path: 'new', component: RoutesNewComponent },
  { path: ':route_id', component: RoutesDetailComponent},
  { path: ':route_id/new', component: AddLandmarkComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoutesRoutingModule { }
