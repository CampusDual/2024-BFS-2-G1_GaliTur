import { AfterViewInit, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { OButtonComponent, ODateInputComponent, OFormComponent, OIntegerInputComponent, OSnackBarConfig, OTextInputComponent, OntimizeService } from 'ontimize-web-ngx';
import { OFormBase } from 'ontimize-web-ngx/lib/components/form/o-form-base.class';

@Component({
  selector: 'app-pack-schedule',
  templateUrl: './pack-schedule.component.html',
  styleUrls: ['./pack-schedule.component.css']
})
export class PackScheduleComponent implements AfterViewInit{
  snackBarService: any;
onInsertPackDate() {
  console.log("Soy el imatiaInsert master")
}

onChangeValue() {
  this.pckIdInput.setData(this.pck_id)
}
  @ViewChild("days") days: OIntegerInputComponent
  @ViewChild("beginDate") beginDate: ODateInputComponent
  @ViewChild("endDate") endDate: ODateInputComponent
  @ViewChild("nameInput") nameInput: OTextInputComponent
  @ViewChild("pckIdInput") pckIdInput: OTextInputComponent
  @ViewChild("formData") formData: OFormComponent
  @ViewChild("InsertPackDateButton") InsertPackDateButton: OButtonComponent
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
  private ontimizePackDateService: OntimizeService
){
  this.configurePackDateService()
}
  packName: any
  pck_id:any
  protected configurePackDateService() {
    const confPackDate =
      this.ontimizePackDateService.getDefaultServiceConfiguration("packDates");
    this.ontimizePackDateService.configureService(confPackDate);
  }
  ngAfterViewInit(): void {
      this.days.setData(this.data.dataToSend.days)
      this.packName= this.data.dataToSend.pck_name
      this.pck_id = this.data.dataToSend.pck_id
      this.InsertPackDateButton.enabled = false
  }
  OnInsertPackDate():any{
    const inicialDate= this.formatDateToSend(this.beginDate.getValueAsDate())
    const finalDate = this.formatDateToSend(this.endDate.getValueAsDate())
    console.log(inicialDate,"     ",finalDate)

    this.ontimizePackDateService
      .insert(
        {
          pd_date_begin:inicialDate,
          pd_date_end:finalDate,
          pcs_id:1,
          pck_id:this.pck_id
        },
        "packDate"
      )
      .subscribe((response) => {
        console.log("QUE PASO CON EL INSERT MAN???: ",response.data)
        const config: OSnackBarConfig = {
          action: "",
          milliseconds: 2000,
          icon: "settings",
          iconPosition: "left",
          cssClass: "snackbar",
        };
        this.snackBarService.open("PACKDATECONFIRMED", config);
      });
      
  }
  formatDateToSend(date:any){
    const yyyy = date.getFullYear();
    const MM = String(date.getMonth() + 1).padStart(2, '0'); 
    const dd = String(date.getDate()).padStart(2, '0');
    const HH = String(date.getHours()).padStart(2, '0');
    const mm = String(date.getMinutes()).padStart(2, '0');
    const ss = String(date.getSeconds()).padStart(2, '0');
    const SSS = String(date.getMilliseconds()).padStart(3, '0');
    
    return `${yyyy}-${MM}-${dd} ${HH}:${mm}:${ss}.${SSS}`;
  }
  getMinDate(){
    const currentDate = new Date()
    return new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
  }
  onBeginDateChanged(){
    if (this.beginDate.getValue()){
      const date = new Date(this.beginDate.getValueAsDate())
      this.endDate.setValue(new Date(date.getFullYear(), date.getMonth(), date.getDate() + Number.parseInt(this.days.getValue())));
      this.InsertPackDateButton.enabled = true
    } else {
      this.endDate.setValue(null)
      this.InsertPackDateButton.enabled = false
    }
  }
  checkDays() {
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
  

}
