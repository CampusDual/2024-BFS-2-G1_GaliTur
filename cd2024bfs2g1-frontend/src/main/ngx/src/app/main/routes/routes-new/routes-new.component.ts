import { Component } from '@angular/core';
import { AbstractControl, FormControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import { OTranslateService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-routes-new',
  templateUrl: './routes-new.component.html',
  styleUrls: ['./routes-new.component.css']
})
export class RoutesNewComponent {
isChecked = true
blankValidator: ValidatorFn[] = [];

getValue(): any {
 return true
}

  constructor(private router:Router, private translate: OTranslateService){
    this.blankValidator.push(this.blanksValidator)
    this.blankValidator.push(this.lengthInvalid)
  }

  onClickOk($event:Event){
    console.log('El id de la ruta es: '+$event['route_id']);
    this.router.navigate(['main','routes', 'new', $event['route_id']])
  }

  onClickCancel(){
    this.router.navigate(['main','routes'])
  }
  
  lengthInvalid = (control: FormControl) => {
    const isTooLong = (control.value || '').length > 500;
    const isValid = !isTooLong;
    return isValid ? null : {'lengthInvalid': true};
  };

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

  public difficultyArray = [{
    difficultyCode: 1,
    difficultyText: this.translate.get("EASY")
  }, {
    difficultyCode: 2,
    difficultyText:  this.translate.get("INTERMEDIATE")
  }, {
    difficultyCode: 3,
    difficultyText:  this.translate.get("HARD")
  }, {
    difficultyCode: 4,
    difficultyText:  this.translate.get("EXTREME")
  }]

}
