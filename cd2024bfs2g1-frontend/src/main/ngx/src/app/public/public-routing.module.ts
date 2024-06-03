import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PublicComponentComponent } from './public-component/public-component.component';
import { PermissionsGuardService } from 'ontimize-web-ngx';

export const routes: Routes = [
    {
      component: PublicComponentComponent,
      path: '',
      canActivateChild: [PermissionsGuardService],
      children: [
        { path: 'packs', loadChildren: () => import('src/app/main/pack/pack.module').then(m => m.PackModule) },
        { path: 'businesses', loadChildren: () => import('src/app/main/business/business.module').then(m => m.BusinessModule) },
        { path: 'routes', loadChildren: () => import('src/app/main/routes/routes.module').then(m => m.RoutesModule) },
        { path: 'home', loadChildren: () => import('src/app/main/home/home.module').then(m => m.HomeModule) },
        { path: '', redirectTo: 'home', pathMatch: 'full' },
        { path: '**', redirectTo: 'home', pathMatch: 'full' }
      ]
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class PublicRoutingModule { }