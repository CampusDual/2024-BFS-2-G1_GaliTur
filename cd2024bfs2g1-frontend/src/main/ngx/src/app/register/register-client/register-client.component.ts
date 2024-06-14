import {AbstractControl, FormControl, ValidationErrors, ValidatorFn} from '@angular/forms';
import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {
  AuthService,
  DialogService,
  ODateInputComponent,
  OEmailInputComponent,
  OFormComponent,
  OPasswordInputComponent,
  OTextInputComponent, OUserInfoService,
  OValidators, ServiceResponse
} from 'ontimize-web-ngx';
import {Router} from "@angular/router";
import {MainService} from "../../shared/services/main.service";
import {UserInfoService} from "../../shared/services/user-info.service";
import {DomSanitizer} from "@angular/platform-browser";

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
              private dialogService: DialogService,
              @Inject(OUserInfoService) private oUserInfoService: OUserInfoService,
              @Inject(AuthService) private authService: AuthService,
              @Inject(UserInfoService) private userInfoService: UserInfoService,
              @Inject(DomSanitizer) private domSanitizer: DomSanitizer){
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
         
         if (result.data[0] === undefined){
          this.form.insert()
        } else if (result.data[0].usr_login === this.login.getValue()){
          this.dialogService.error('Username error', 'Username already exists');
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
    const login = this.login.getValue();
    const password = this.password.getValue();
    if (login && login.length > 0 && password && password.length > 0) {
      this.authService.login(login, password)
        .subscribe(() => {
          this.mainService.getUserInfo()
            .subscribe(
              (result: ServiceResponse) => {
                let avatar = './assets/images/user_profile.png';
                this.userInfoService.storeUserInfo(result.data);
                if (result.data['usr_photo']) {
                  (avatar as any) = this.domSanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + result.data['usr_photo']);
                }
                this.oUserInfoService.setUserInfo({
                  username: result.data['usr_name'],
                  avatar: avatar
                });
              }
            );
          this.router.navigate(['main']);
        });
    }
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
        const regex = /^[A-Za-z_\-.\d]*$/
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
