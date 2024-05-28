import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PublicComponentComponent } from './public-component/public-component.component';

export const routes: Routes = [
    {
      component: PublicComponentComponent,
      path: '',
      children: [
        { path: 'packs', loadChildren: () => import('src/app/main/pack/pack.module').then(m => m.PackModule) },
        { path: 'businesses', loadChildren: () => import('src/app/main/business/business.module').then(m => m.BusinessModule) },
        { path: 'routes', loadChildren: () => import('src/app/main/routes/routes.module').then(m => m.RoutesModule) },
        { path: '', redirectTo: 'packs', pathMatch: 'full' },
        { path: '**', redirectTo: 'packs', pathMatch: 'full' }
      ]
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class PublicRoutingModule { }