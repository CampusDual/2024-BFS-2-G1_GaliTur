import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoutesHomeComponent } from './routes-home/routes-home.component';
import { RoutesNewComponent } from './routes-new/routes-new.component';
import { RoutesDetailComponent } from './routes-detail/routes-detail.component';

const routes: Routes = [
  { path: '', component: RoutesHomeComponent },
  { path: 'new', component: RoutesNewComponent },
  { path: ':route_id', component: RoutesDetailComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoutesRoutingModule { }
