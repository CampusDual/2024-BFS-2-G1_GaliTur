import {AfterViewInit, Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {
  AbstractControl, FormControl,
  ValidationErrors,
  ValidatorFn
} from '@angular/forms';
import {
  DialogService,
  OComboComponent, ODateInputComponent, OEmailInputComponent,
  OFormComponent, OPasswordInputComponent,
  OTextInputComponent,
  OValidators,
  ServiceResponse
} from 'ontimize-web-ngx';
import {Router} from "@angular/router";
import {MainService} from "../../shared/services/main.service";

@Component({
  selector: 'app-register-client',
  templateUrl: './register-client.component.html',
  styleUrls: ['./register-client.component.css']
})
export class RegisterClientComponent{

  validatorsNewPasswordArray: ValidatorFn[] = [];
  @ViewChild('form') form: OFormComponent;
  @ViewChild('login') login: OTextInputComponent;
  @ViewChild('name') name: OTextInputComponent;
  @ViewChild('surname') surname: OTextInputComponent;
  @ViewChild('birth_date') birth_date: ODateInputComponent;
  @ViewChild('email') email: OEmailInputComponent;
  @ViewChild('password') password: OPasswordInputComponent;
  @ViewChild('newPassword') newPassword: OPasswordInputComponent;

  constructor(private router: Router, @Inject(MainService) private mainService: MainService,
              private dialogService: DialogService){
    // check whether the entered password has a number
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(/\d/, 'hasNumber'));
    // check whether the entered password has upper case letter
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(/[A-Z]/, 'hasCapitalCase'));
    // check whether the entered password has small case character
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(/[a-z]/, 'hasSmallCase'));
    // check whether the entered password has a special character
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(/[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/,
      'hasSpecialCharacters'));
  }

  public register(){
    let isRegisterOk = true

    if (this.login.getValue() === undefined || this.name.getValue() === undefined  ||
      this.surname.getValue() === undefined || this.birth_date.getValue() === undefined ||
      this.email.getValue() === undefined || this.password.getValue() === undefined ||
      this.newPassword.getValue() === undefined){
      this.dialogService.error('Register Error', 'Some required fields are empty')
      isRegisterOk = false
    }
    if (!isRegisterOk) {
      return
    }

    this.mainService.getUserInfoByLoginAndId(this.login.getValue(), this.email.getValue()).subscribe(
      (result) => {
        if (result.data[0] === undefined){
          this.form.insert()
          this.router.navigate(['/login'])
        } else if (result.data[0].usr_login === this.login.getValue()){
          this.dialogService.error('Username error', 'Username already exists'); // Use DialogService to show error
          } else if (result.data[0].usr_email === this.email.getValue()){
          this.dialogService.error('Email error', 'Email already exists')
        }
      }
    )
  }
  newPasswordMatchValidator(control: AbstractControl): ValidationErrors {
    const newPassword = control.parent?.get('usr_password') as FormControl;
    const confirmNewPassword = control.parent?.get('confirm_new_password') as FormControl;
    return newPassword && confirmNewPassword && newPassword.value === confirmNewPassword.value ? null : { matchNewPassword: true };
  }
}
