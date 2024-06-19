import { DomSanitizer } from '@angular/platform-browser';
import { Component, Inject, OnInit, ViewEncapsulation, HostListener,Renderer2 } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {AuthService, NavigationService, ServiceResponse, OUserInfoService, DialogService} from 'ontimize-web-ngx';
import { Observable } from 'rxjs';
import { MainService } from '../shared/services/main.service';
import { UserInfoService } from '../shared/services/user-info.service';

@Component({
  selector: 'login',
  styleUrls: ['./login.component.scss'],
  templateUrl: './login.component.html',
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {
  
  coverEyes(): void {
    const leftHand = document.querySelector('.hand.left') as HTMLElement;
    const rightHand = document.querySelector('.hand.right') as HTMLElement;
    const mouth = document.querySelector('.mouth') as HTMLElement;
    const eyeR = document.querySelector('.eye.right') as HTMLElement;
    const eyeL = document.querySelector('.eye.left') as HTMLElement;
    const pupilR = document.querySelector('.pupil.right') as HTMLElement;
    const pupilL = document.querySelector('.pupil.left') as HTMLElement;

    eyeR.style.background = '#818f45';
    eyeL.style.background = '#818f45';
    pupilR.style.visibility = 'hidden';
    pupilL.style.visibility = 'hidden';
    mouth.style.borderRadius = '50%';
    mouth.style.height = '20px'; // Ajusta el tamaño del círculo según sea necesario
    mouth.style.width='13px';
    mouth.style.height='13px';
    leftHand.style.transform = 'translateX(80%) translateY(-40%) rotate(-140deg)';
    rightHand.style.transform = 'translateX(-80%) translateY(-40%) rotate(140deg)';
  }

  uncoverEyes(): void {
    const leftHand = document.querySelector('.hand.left') as HTMLElement;
    const rightHand = document.querySelector('.hand.right') as HTMLElement;
    const mouth = document.querySelector('.mouth') as HTMLElement;
    const eyeR = document.querySelector('.eye.right') as HTMLElement;
    const eyeL = document.querySelector('.eye.left') as HTMLElement;
    const pupilR = document.querySelector('.pupil.right') as HTMLElement;
    const pupilL = document.querySelector('.pupil.left') as HTMLElement;

    eyeR.style.background = 'White';
    eyeL.style.background = 'White';
    pupilR.style.visibility = 'visible';
    pupilL.style.visibility = 'visible';
    pupilR.style.background = 'black';
    pupilL.style.background = 'black';
    mouth.style.borderTopLeftRadius = '30%';
    mouth.style.borderTopRightRadius = '30%';
    mouth.style.borderBottomLeftRadius = '100%';
    mouth.style.borderBottomRightRadius = '100%';
    mouth.style.height = '5px';
    mouth.style.width='25px';
    mouth.style.height='10px';
    leftHand.style.transform = 'translateX(-0%) rotate(-0deg)';
    rightHand.style.transform = 'translateX(0%) rotate(0deg)';

  }

  public loginForm: UntypedFormGroup = new UntypedFormGroup({});
  public userCtrl: UntypedFormControl = new UntypedFormControl('', Validators.required);
  public pwdCtrl: UntypedFormControl = new UntypedFormControl('', Validators.required);

  public sessionExpired = false;
  private redirect = '/main';

  constructor(
    private actRoute: ActivatedRoute,
    private renderer: Renderer2,
    private router: Router,
    protected dialogService: DialogService,
    @Inject(NavigationService) private navigationService: NavigationService,
    @Inject(AuthService) private authService: AuthService,
    @Inject(MainService) private mainService: MainService,
    @Inject(OUserInfoService) private oUserInfoService: OUserInfoService,
    @Inject(UserInfoService) private userInfoService: UserInfoService,
    @Inject(DomSanitizer) private domSanitizer: DomSanitizer
  ) {
    const qParamObs: Observable<any> = this.actRoute.queryParams;
    qParamObs.subscribe(params => {
      if (params) {
        if (params['session-expired']) {
          this.sessionExpired = (params['session-expired'] === 'true');
        } else {
          if (params['redirect']) {
            this.redirect = params['redirect'];
          }
          this.sessionExpired = false;
        }
      }
    });
  }

  ngOnInit(): any {
    this.navigationService.setVisible(false);

    this.loginForm.addControl('username', this.userCtrl);
    this.loginForm.addControl('password', this.pwdCtrl);

    if (this.authService.isLoggedIn()) {
      this.router.navigate([this.redirect]);
    } else {
      this.authService.clearSessionData();
    }
  }

  @HostListener('document:mousemove', ['$event'])
  onMouseMove(event: MouseEvent) {
    const pupils = document.querySelectorAll('.pupil');
    const eyes = document.querySelectorAll('.eye');

    pupils.forEach((pupil, index) => {
      const eye = eyes[index];
      const rect = eye.getBoundingClientRect();

      const eyeX = rect.left + rect.width / 2;
      const eyeY = rect.top + rect.height / 2;

      const angle = Math.atan2(event.clientY - eyeY, event.clientX - eyeX); // Calculamos el ángulo

      const distanceX =9;
      const distanceY = 12; // Distancia de movimiento de la pupila
      const pupilX = Math.cos(angle) * distanceX; // Movimiento horizontal
      const pupilY = Math.sin(angle) * distanceY; // Movimiento vertical

      this.renderer.setStyle(pupil, 'transform', `translate(${pupilX}px, ${pupilY}px)`);
    });
  }


  public login() {
    const userName = this.loginForm.value.username;
    const password = this.loginForm.value.password;
    if (userName && userName.length > 0 && password && password.length > 0) {
      const self = this;
      this.authService.login(userName, password)
        .subscribe(() => {
          self.sessionExpired = false;
          this.loadUserInfo();
          self.router.navigate([this.redirect]);
        }, this.handleError);
    }
  }

  private loadUserInfo() {
    this.mainService.getUserInfo()
      .subscribe(
        (result: ServiceResponse) => {
          this.userInfoService.storeUserInfo(result.data);
          let avatar = './assets/images/user_profile.png';
          if (result.data['usr_photo']) {
            (avatar as any) = this.domSanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + result.data['usr_photo']);
          }
          this.oUserInfoService.setUserInfo({
            username: result.data['usr_name'],
            avatar: avatar
          });
        }
      );
  }

  private handleError(error) {
    switch (error.status) {
      case 401:
        console.error('Email or password is wrong.');
        break;
      default: break;
    }
  }

  goToRegistrerProfesional() {
    this.router.navigate([ '../', 'register', 'professional'], { relativeTo: this.actRoute })
  }
  goToRegistrer() {
    this.router.navigate([ '../', 'register'], { relativeTo: this.actRoute })
  }

  goHome(){
    this.router.navigate(['home'])
  }
}
