import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterMerchantComponent } from './register-merchant/register-merchant.component';

const routes: Routes = [
  {path: '', component: RegisterMerchantComponent},
  {path: 'professional', component: RegisterMerchantComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegisterRoutingModule { }
