import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterClientComponent } from './register-client/register-client.component';
import { RegisterMerchantComponent } from './register-merchant/register-merchant.component';

const routes: Routes = [
  {path: '', component: RegisterClientComponent},
  {path: 'professional', component: RegisterMerchantComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegisterRoutingModule { }
