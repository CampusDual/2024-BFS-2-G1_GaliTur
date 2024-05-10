import { Component, Injector, ViewChild } from '@angular/core';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import { ODateInputComponent, OTranslateService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-pack-new',
  templateUrl: './pack-new.component.html',
  styleUrls: ['./pack-new.component.css']
})
export class PackNewComponent {
  numero: number = 0;
  @ViewChild("dateBegin", { static: false })
  dateBegin: ODateInputComponent;
  @ViewChild("dateEnd", { static: false })
  dateEnd: ODateInputComponent;
  blankValidator: ValidatorFn[] = [];
  constructor(public injector: Injector, private translate: OTranslateService, private router:Router) {
    this.blankValidator.push(this.blanksValidator)
    this.dateBegin = null;
    this.dateEnd = null;

  }
  insertPacks($event:Event){
    this.router.navigate(['main/packs/'])

  }

  blanksValidator(control: AbstractControl): ValidationErrors | null{
    try{
      const blank = /^[a-zA-Z\d][a-zA-Z\s\d]*[a-zA-Z\d]$/;
      const inputValue = control.value;

      if(blank.test(inputValue)){
        return null;
      } else {
        return { blankInvalid: true };
      }
    } catch (e){}
  }
  getDate(): number {
    const fechaActual: Date = new Date();
    return Math.floor(fechaActual.getTime());
  }
  dateChange() {
    if(this.dateBegin.getValue() === undefined){
      this.dateEnd.enabled = "no"
      this.dateEnd.setValue(null)
    }else{
      this.dateEnd.enabled = "yes"
      this.numero = this.dateBegin.getValue()
    }
  }
  getDateEnd(): number {
    if (this.dateEnd !== null) {
      return this.dateBegin.getValue();
    } else {
      return this.getDate();
    }
  }


}
