import {Component, Inject, Injector, ViewChild} from '@angular/core';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import moment from 'moment';
import {ODateInputComponent, OIntegerInputComponent, OntimizeService, OTranslateService} from 'ontimize-web-ngx';
import {MainService} from "../../../shared/services/main.service";

@Component({
  selector: 'app-pack-new',
  templateUrl: './pack-new.component.html',
  styleUrls: ['./pack-new.component.css']
})
export class PackNewComponent {
  nameValidators: ValidatorFn[] = [];
  descValidators: ValidatorFn[] = [];
  @ViewChild("days") days: OIntegerInputComponent
  @ViewChild("beginDate") beginDate: ODateInputComponent
  @ViewChild("endDate") endDate: ODateInputComponent
  constructor(public injector: Injector, private translate: OTranslateService, private router:Router,
              @Inject(MainService) private mainService: MainService,
              private ontimizeService: OntimizeService) {
    this.nameValidators.push(this.blanksValidator)
    this.descValidators.push(this.blanksValidator)
    this.descValidators.push(this.descLengthValidator)

    this.configureService()
  }
  private configureService() {
    const conf = this.ontimizeService.getDefaultServiceConfiguration('packs');
    this.ontimizeService.configureService(conf);
  }

  protected async insertPacks() {
    this.ontimizeService.query({}, ['pck_id'], 'newest')
      .subscribe(
        (response) => {
          this.router.navigate(['main/packs/', response.data[0].pck_id]);
        }
      );
  }

  blanksValidator(control: AbstractControl): ValidationErrors | null{
    try{
      const blank = /^\S+.+\S+$|^\S+$/;
      const inputValue = control.value;


      if(blank.test(inputValue)){
        return null;
      } else {
        return { blankInvalid: true };
      }
    } catch (e){}
  }

  descLengthValidator(control: AbstractControl): ValidationErrors | null{
    try{
      const regex = /^.{1,250}$/;
      const inputValue = control.value;


      if(regex.test(inputValue)){
        return null;
      } else {
        return { descLengthInvalid: true };
      }
    } catch (e){}
  }
  getMinDate(){
    const currentDate = new Date()
    return new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
  }

  checkDays() {
    console.log("onchange")
    if (this.days.getValue() <= 0 || this.days.getValue() === undefined){
      this.beginDate.setEnabled(false)
      this.endDate.setValue(null)
    } else {
      this.beginDate.setEnabled(true)
      if (this.beginDate.getValueAsDate()){
        this.onBeginDateChanged()
      }
    }
  }

  onBeginDateChanged(){
    if (this.beginDate.getValue()){
      const date = new Date(this.beginDate.getValueAsDate())
      console.log(date.getDate())
      this.endDate.setValue(new Date(date.getFullYear(), date.getMonth(), date.getDate() + Number.parseInt(this.days.getValue())));
    } else {
      this.endDate.setValue(null)
    }
  }

}
