import {AbstractControl, FormControl, ValidationErrors, ValidatorFn} from '@angular/forms';
import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {
  DialogService,
  ODateInputComponent,
  OEmailInputComponent,
  OFormComponent,
  OPasswordInputComponent,
  OTextInputComponent,
  OValidators
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
  validatorsUsernameArray: ValidatorFn[] = [];
  validatorsNameArray: ValidatorFn[] = [];
  @ViewChild('form') form: OFormComponent;
  @ViewChild('login') login: OTextInputComponent;
  @ViewChild('name') name: OTextInputComponent;
  @ViewChild('surname') surname: OTextInputComponent;
  @ViewChild('birth_date') birth_date: ODateInputComponent;
  @ViewChild('email') email: OEmailInputComponent;
  @ViewChild('password') password: OPasswordInputComponent;
  @ViewChild('newPassword') newPassword: OPasswordInputComponent;

  private regexNumber = /\d/;
  private regexSpecialChar = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
  private regexSmallCase = /[a-z]/;
  private regexCaps = /[A-Z]/;

  constructor(private router: Router, @Inject(MainService) private mainService: MainService,
              private dialogService: DialogService){
    // check whether the entered password has a number
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(this.regexNumber, 'hasNumber'));
    // check whether the entered password has upper case letter
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(this.regexCaps, 'hasCapitalCase'));
    // check whether the entered password has small case character
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(this.regexSmallCase, 'hasSmallCase'));
    // check whether the entered password has a special character
    this.validatorsNewPasswordArray.push(OValidators.patternValidator(this.regexSpecialChar,
      'hasSpecialCharacters'));

    this.validatorsUsernameArray.push(this.usernameLengthValidator)
    this.validatorsUsernameArray.push(this.blanksValidator)
    this.validatorsUsernameArray.push(this.usernameCharsValidator)

    this.validatorsNameArray.push(this.nameBlanksBetweenValidator)
  }

  public register(){
     this.mainService.getUserInfoByLoginAndId(this.login.getValue(), this.email.getValue()).subscribe(
       (result) => {
         this.form.insert()
         if (result.data[0] === undefined){
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

  navLogin() {
    this.router.navigate(['/login'])
  }

  navRegisterProfessional() {
    this.router.navigate(['/register', 'professional'])
  }

  navigate() {
    this.router.navigate(['/main'])
  }

  getMinAge() {
    const currentDate = new Date();
    return new Date(currentDate.getFullYear() - 18, currentDate.getMonth(), currentDate.getDate());
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
