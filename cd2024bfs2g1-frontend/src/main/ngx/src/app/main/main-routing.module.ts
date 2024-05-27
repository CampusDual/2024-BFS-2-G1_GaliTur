import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from 'ontimize-web-ngx';
import { MainComponent } from './main.component';
import { ProfileComponent } from './profile/profile.component';

export const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      { path: '', redirectTo: 'packs', pathMatch: 'full' },
      { path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
      { path: 'admin', canActivate: [AuthGuardService], loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
      { path: 'settings', canActivate: [AuthGuardService], loadChildren: () => import('./settings/settings.module').then(m => m.SettingsModule) },
      { path: 'businesses', canActivate: [AuthGuardService], loadChildren: () => import('./business/business.module').then(m => m.BusinessModule) },
      { path: 'profile', canActivate: [AuthGuardService], component: ProfileComponent },
      { path: 'routes', canActivate: [AuthGuardService], loadChildren: () => import('./routes/routes.module').then(m => m.RoutesModule) },
      { path: 'packs', loadChildren: () => import('./pack/pack.module').then(m => m.PackModule) },
      { path: 'pack-client', canActivate: [AuthGuardService], loadChildren: () => import('./my-packs/my-packs.module').then(m => m.MyPacksModule) },
      { path: 'graphics', canActivate: [AuthGuardService], loadChildren: () => import('./graphics/graphics.module').then(m => m.GraphicsModule) },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
