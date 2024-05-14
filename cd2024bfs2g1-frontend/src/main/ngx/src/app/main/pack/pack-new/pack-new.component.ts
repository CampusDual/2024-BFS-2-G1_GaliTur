import { Component, Injector, ViewChild } from '@angular/core';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import moment from 'moment';
import { ODateInputComponent, OTranslateService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-pack-new',
  templateUrl: './pack-new.component.html',
  styleUrls: ['./pack-new.component.css']
})
export class PackNewComponent {
  blankValidator: ValidatorFn[] = [];
  constructor(public injector: Injector, private translate: OTranslateService, private router:Router) {
    this.blankValidator.push(this.blanksValidator)
  }
  insertPacks($event:Event){
    this.router.navigate(['main/packs/'])

  }

  blanksValidator(control: AbstractControl): ValidationErrors | null{
    try{
      const blank = /^\S*.+\S*$/;
      const inputValue = control.value;

      if(blank.test(inputValue)){
        return null;
      } else {
        return { blankInvalid: true };
      }
    } catch (e){}
  }
  getDate(): moment.Moment {
    return moment()
  }

}
