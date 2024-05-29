import { AfterViewInit, Component, ElementRef, Injector, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { NavigationService, OButtonComponent, OTableColumn, OTableColumnComponent, OTextInputComponent, OValueChangeEvent, OntimizeService } from 'ontimize-web-ngx';
import { PackScheduleComponent } from './pack-schedule/pack-schedule.component';
import { AbstractControl, FormControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { MatButton } from '@angular/material/button';

@Component({
  selector: 'app-pack-edit',
  templateUrl: './pack-edit.component.html',
  styleUrls: ['./pack-edit.component.css']
})
export class PackEditComponent implements AfterViewInit{
  blankValidator: ValidatorFn[] = [];

  @ViewChild('packNameInput') packNameInput : ElementRef
  @ViewChild('addPDbtn') packDateButton : ElementRef
  @ViewChild('addRaBbtn') rtandbssButton : ElementRef
  @ViewChild('dateBeginColumnRenderer',{ static: true }) dateBeginColumnRenderer : OTableColumnComponent
  constructor(private router: Router,
    private activeRoute: ActivatedRoute,
    protected injector: Injector,
    private dialog: MatDialog
  ){
    this.injector.get(NavigationService).initialize();
  }

  ngAfterViewInit(): void {
    this.pck_id = this.getPackId()
    console.log(this.dateBeginColumnRenderer)
    console.log(this.dateBeginColumnRenderer.table)

  }
  private actionButtons(disable:boolean){
    this.packDateButton.nativeElement.disabled = disable;
    this.rtandbssButton.nativeElement.disabled = disable;
  }
   onAddRtsOrBss(){
      this.router.navigate(["main", "packs","new",this.pck_id]);
      /*otra forma para probar this.router.navigate(['main','routes', 'new', $event['route_id']])*/
  }
  pck_id: any =""
  imgId: any;
  dataInputName: any
  dataInputDays: any
  firstNameData:boolean = true
  firstDayData:boolean = true
  dateBeginWithFormat: any
  packDate:any = {
    name:'',
    days:0,
  }
  getPackId():number{
    return +this.activeRoute.snapshot.params["pck_id"];
  }
  onClicEdit(pck_id: any) {
    this.router.navigate(['main','packs','new',pck_id])
  }
  

  onClicSchedule(){
    const dataToSend = {
      pck_name:this.dataInputName,
      pck_id:this.pck_id,
      days:this.dataInputDays
    }
    this.dialog.open(PackScheduleComponent,{
      width: '800',
      maxHeight: '1000',
      minHeight: '1000',
      maxWidth: '80vw',
      data: {
        dataToSend
      }
    })
  }
  onNameChange(data: OValueChangeEvent) {
    if(this.firstNameData === true){
      this.packDate.name= data.target.value.value
      this.dataInputName= data.target.value.value
      this.firstNameData= false
    }else {
      this.dataInputName= data.target.value.value
      if(data.target.value.value===this.packDate.name) this.actionButtons(false)
        else this.actionButtons(true)
    }

  }
  onDaysChange(data:OValueChangeEvent) {
    if(this.firstDayData === true){
      this.packDate.days= data.target.value.value
      this.dataInputDays= data.target.value.value
      this.firstDayData= false
    }else {
      this.dataInputDays= data.target.value.value
      if(data.target.value.value===this.packDate.days) this.actionButtons(false)
        else this.actionButtons(true)
    }
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
  onSaveChanges(){
    this.actionButtons(false)
    this.firstDayData = true
    this.firstDayData = true
  }
  ConvertTime(pd_id: any){
    console.log(12)
  }
}






