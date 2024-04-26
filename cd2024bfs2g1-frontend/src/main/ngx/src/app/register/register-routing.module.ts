import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterHomeComponent} from "./register-home/register-home.component";

const routes: Routes = [
  {path: '', component: RegisterHomeComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegisterRoutingModule { }
