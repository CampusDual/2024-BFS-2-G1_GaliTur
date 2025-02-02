import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService, PermissionsGuardService } from 'ontimize-web-ngx';
import { MainComponent } from './main.component';
import { ProfileComponent } from './profile/profile.component';

export const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivateChild: [PermissionsGuardService],
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
      { path: 'admin', canActivate: [AuthGuardService], loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
      { path: 'settings', canActivate: [AuthGuardService], loadChildren: () => import('./settings/settings.module').then(m => m.SettingsModule) },
      { path: 'businesses', loadChildren: () => import('./business/business.module').then(m => m.BusinessModule) },
      { path: 'business-merchant', loadChildren: () => import('./my-businesses/my-businesses.module').then(m => m.MyBusinessesModule) },
      { path: 'profile', canActivate: [AuthGuardService], component: ProfileComponent },
      { path: 'routes', loadChildren: () => import('./routes/routes.module').then(m => m.RoutesModule) },
      { path: 'packs', loadChildren: () => import('./pack/pack.module').then(m => m.PackModule) },
      { path: 'pack-client', canActivate: [AuthGuardService], loadChildren: () => import('./my-packs/my-packs.module').then(m => m.MyPacksModule) },
      { path: 'graphics', canActivate: [AuthGuardService], loadChildren: () => import('./graphics/graphics.module').then(m => m.GraphicsModule) },
      { path: 'pack-manage', canActivate: [AuthGuardService], loadChildren: () => import('./manage-packages/manage-packages.module').then(m => m.ManagePackagesModule) },
      { path: 'route-manage', canActivate: [AuthGuardService], loadChildren: () => import('./routes-manage/routes-manage.module').then(m => m.RoutesManageModule) },

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
