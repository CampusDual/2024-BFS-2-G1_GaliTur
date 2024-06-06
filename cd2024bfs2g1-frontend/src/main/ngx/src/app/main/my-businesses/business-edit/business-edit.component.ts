import { Component, Injector, ViewChild } from "@angular/core";
import {
  AbstractControl,
  FormControl,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import { DomSanitizer } from "@angular/platform-browser";
import { Router } from "@angular/router";
import { OCheckboxComponent, OCurrencyInputComponent, OPermissions, OTranslateService, Util } from "ontimize-web-ngx";

@Component({
  selector: "app-business-edit",
  templateUrl: "./business-edit.component.html",
  styleUrls: ["./business-edit.component.css"]
})
export class BusinessEditComponent {



  insertBusiness($event:Event){
    //this.router.navigate([ "main/business-merchant", data[0].bsn_id,"edit"])

  }

  selectedOption: number;
  validatorsDniCif: ValidatorFn[] = [];
  blankValidator: ValidatorFn[] = [];

  public switchDestinationState: boolean = false;
  @ViewChild("switchDestination", { static: false })
  switchDestination: OCheckboxComponent;
  @ViewChild("currency1", { static: false }) currency1: OCurrencyInputComponent;

  public switchDestinationState2: boolean = false;
  @ViewChild("switchDestination2", { static: false })
  switchDestination2: OCheckboxComponent;
  @ViewChild("currency2", { static: false }) currency2: OCurrencyInputComponent;

  public switchDestinationState3: boolean = false;
  @ViewChild("switchDestination3", { static: false })
  switchDestination3: OCheckboxComponent;
  @ViewChild("currency3", { static: false }) currency3: OCurrencyInputComponent;

  constructor(public injector: Injector, private translate: OTranslateService, private router:Router, protected sanitizer: DomSanitizer) {
    this.validatorsDniCif.push(this.dniAndCifValidator);
    this.blankValidator.push(this.blanksValidator)
    this.blankValidator.push(this.lengthInvalid)
  }


  getSwitchValue() {
    this.switchDestinationState = this.switchDestination.getValue();
    this.currency1.setValue(null);
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }


  getSwitchValue2() {
    this.switchDestinationState2 = this.switchDestination2.getValue();
    this.currency2.setValue(null);
  }


  getSwitchValue3() {
    this.switchDestinationState3 = this.switchDestination3.getValue();
    this.currency3.setValue(null);
  }

  lengthInvalid = (control: FormControl) => {
    const isTooLong = (control.value || '').length > 500;
    const isValid = !isTooLong;
    return isValid ? null : {'lengthInvalid': true};
  };

  setSelectedOption($event: any) {
    this.selectedOption = $event;
  }

  dniAndCifValidator(control: AbstractControl): ValidationErrors | null {
    try {
      const cifRegex = /^([A-Z])(\d{8})$/;
      const dniRegex = /^(\d{8}[A-Za-z])$/;
      const inputValue = control.value.trim();

      if (cifRegex.test(inputValue) || dniRegex.test(inputValue)) {
        if (dniRegex.test(inputValue)) {
          const dniNumber = Number(inputValue.substring(0, 8));
          const dniLetter = inputValue.substring(8).toUpperCase();
          const letterIndex = dniNumber % 23;
          const letters = "TRWAGMYFPDXBNJZSQVHLCKE";
          const expectedLetter = letters.charAt(letterIndex);

          if (dniLetter === expectedLetter) {
            return null;
          } else {
            return { invalidDniLetter: true };
          }
        } else {
          return null;
        }
      } else {
        return { dniOrCifFormatError: true };
      }
    } catch (e) {}
  }


  blanksValidator(control: AbstractControl): ValidationErrors | null{
    try{
      const blank = /^[a-zA-Z].*/;
      const inputValue = control.value.trim();

      if(blank.test(inputValue)){
        return null;
      } else {
        return { blankInvalid: true };
      }
    } catch (e){}
  }

  getDataArray(): any {
    const array: Array<Object> = [];
    array.push({
      key: 1,
      value: this.translate.get("Restaurant"),
    });
    array.push({
      key: 2,
      value: this.translate.get("Lodging"),
    });
    array.push({
      key: 3,
      value: this.translate.get("AgencyGuide"),
    });
    return array;
  }

}
