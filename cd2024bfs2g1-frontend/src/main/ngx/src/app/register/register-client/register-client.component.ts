import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { OFormComponent, OValidators } from 'ontimize-web-ngx';

@Component({
  selector: 'app-register-client',
  templateUrl: './register-client.component.html',
  styleUrls: ['./register-client.component.css']
})
export class RegisterClientComponent{
  
  validatorsNewPasswordArray: ValidatorFn[] = [];

  public staticData=[{id: 0, name: 'merchant'},{id: 1, name: 'guide'}];

  constructor(){
    // check whether the entered password has a number
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(/\d/, 'hasNumber'));
    // check whether the entered password has upper case letter
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(/[A-Z]/, 'hasCapitalCase'));
    // check whether the entered password has small case character
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(/[a-z]/, 'hasSmallCase'));
    // check whether the entered password has a special character
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(/[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/, 'hasSpecialCharacters'));
  }

  public register(){
    
  }
  newPasswordMatchValidator(control: UntypedFormControl): ValidationErrors {
    const newPassword = control.parent.controls['new_password'];
    const confirmNewPassword = control.parent.controls['confirm_new_password'];

    return newPassword && confirmNewPassword && newPassword.value === confirmNewPassword.value ? null : { matchNewPassword: true };
  }
}
