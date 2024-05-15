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
  validatorsUsernameArray: ValidatorFn[] = [];
  validatorsNameArray: ValidatorFn[] = [];
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

    this.validatorsUsernameArray.push(this.usernameLengthValidator)
    this.validatorsUsernameArray.push(this.blanksValidator)
    this.validatorsUsernameArray.push(this.usernameCharsValidator)

    this.validatorsNameArray.push(this.nameBlanksBetweenValidator)
  }

  public register(){
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

  blanksValidator(control: AbstractControl): ValidationErrors | null{
    try{
      const regex = /^\S*$/;
      const inputValue = control.value;

      if(regex.test(inputValue)){
        return null;
      } else {
        return { blanks: true };
      }
    } catch (e){}
  }

  usernameLengthValidator(control: AbstractControl): ValidationErrors | null{
    try{
      const regex = /^.{6,20}$/;
      const inputValue = control.value;

      if(regex.test(inputValue)){
        return null;
      } else {
        return { usernameLength: true };
      }
    } catch (e){}
  }

  usernameCharsValidator(control: AbstractControl): ValidationErrors | null{
    try{
      const regex = /^[A-Za-z_\-.]*$/
      const inputValue = control.value;

      if(regex.test(inputValue)){
        return null;
      } else {
        return { usernameChars: true };
      }
    } catch (e){}
  }

  nameBlanksBetweenValidator(control: AbstractControl): ValidationErrors | null{
    try{
      const regex = /^\S(?:.*\S)?$/
      const inputValue = control.value;

      if(regex.test(inputValue)){
        return null;
      } else {
        return { nameBlanksBetween: true };
      }
    } catch (e){}
  }
}
