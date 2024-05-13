import { Component } from '@angular/core';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import { OTranslateService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-routes-new',
  templateUrl: './routes-new.component.html',
  styleUrls: ['./routes-new.component.css']
})
export class RoutesNewComponent {
isChecked = true
getValue(): any {
 return true
}

  constructor(private router:Router, private translate: OTranslateService){
    this.blankValidator.push(this.blanksValidator)

  }

  onClickOk($event:Event){
    this.router.navigate(['main/routes'])

  }

  blankValidator: ValidatorFn[] = [];
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
