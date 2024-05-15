import {Component, Inject, ViewChild} from '@angular/core';
import {AbstractControl, FormControl, ValidationErrors, ValidatorFn} from "@angular/forms";
import {DialogService, OFormComponent, OPasswordInputComponent, OTextInputComponent, OValidators} from "ontimize-web-ngx";
import {Router} from "@angular/router";
import {MainService} from "../../shared/services/main.service";

@Component({
  selector: 'app-register-merchant',
  templateUrl: './register-merchant.component.html',
  styleUrls: ['./register-merchant.component.css']
})
export class RegisterMerchantComponent {
  validatorsNewPasswordArray: ValidatorFn[] = [];
  @ViewChild('form') form: OFormComponent;
  @ViewChild('login') login: OTextInputComponent;
  @ViewChild('email') email: OTextInputComponent;
  @ViewChild('name') name: OTextInputComponent;
  @ViewChild('surname') surname: OTextInputComponent;
  @ViewChild('password') password: OPasswordInputComponent;
  @ViewChild('newPassword') newPassword: OPasswordInputComponent;

  private regexNumber = /\d/;

  private regexSpecialChar = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;

  private regexSmallCase = /[a-z]/;

  private regexCaps = /[A-Z]/;

  private regexEmail = /^[\w.%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

  constructor(private router: Router, @Inject(MainService) private mainService: MainService, private dialogService: DialogService,){
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
    let isRegisterOk: boolean = true

    if (
      this.login.getValue() === undefined ||
      this.login.getValue().trim() === '' ||
      this.name.getValue() === undefined ||
      this.name.getValue().trim() === '' ||
      this.surname.getValue() === undefined  ||
      this.surname.getValue().trim() === '' ||
      this.email.getValue() === undefined ||
      this.email.getValue().trim() === '' ||
      this.password.getValue() === undefined ||
      this.password.getValue().trim() === '' ||
      this.newPassword.getValue().trim() === undefined ||
      this.newPassword.getValue().trim() === ''
    ){
      this.dialogService.error('Register Error', 'Some required fields are empty')
      return;
    } else if (!this.regexEmail.test(this.email.getValue().trim())){
      this.dialogService.error('Email Error', 'The email is not valid');
      return;
    } else if (!this.regexSpecialChar.test(this.form.formGroup.controls['usr_password'].value)) {
      this.dialogService.error('Password Error', 'Missing special character on password');
      return;
    }else if (!this.regexNumber.test(this.form.formGroup.controls['usr_password'].value)) {
      this.dialogService.error('Password Error', 'Missing number on password');
      return;
    }else if (!this.regexSmallCase.test(this.form.formGroup.controls['usr_password'].value)) {
      this.dialogService.error('Password Error', 'Missing small case character on password');
      return;
    }else if (!this.regexCaps.test(this.form.formGroup.controls['usr_password'].value)) {
      this.dialogService.error('Password Error', 'Missing capital case character on password');
      return;
    } else if (this.form.formGroup.controls['usr_password'].value !== this.form.formGroup.controls['confirm_new_password'].value) {
      this.dialogService.error('Password Error', 'Passwords doesnt match');
      return;
    }
    if(!isRegisterOk){
      return
    }
    this.mainService.getUserInfoByLoginAndId(this.login.getValue(), this.email.getValue()).subscribe(
      (result) => {
        if (result.data[0] === undefined){
          this.form.insert()
          this.router.navigate(['/login'])
        } else if (result.data[0].usr_login === this.login.getValue()){
          this.dialogService.error('Login error', 'Username already exists');
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

  navLogin() {
    this.router.navigate(['/login'])
  }

  navRegister() {
    this.router.navigate(['/register'])
  }
}
