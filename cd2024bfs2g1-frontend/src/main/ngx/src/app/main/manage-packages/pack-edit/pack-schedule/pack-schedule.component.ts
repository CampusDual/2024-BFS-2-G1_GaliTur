import { Component, Inject, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ODateInputComponent, OIntegerInputComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-pack-schedule',
  templateUrl: './pack-schedule.component.html',
  styleUrls: ['./pack-schedule.component.css']
})
export class PackScheduleComponent {
  @ViewChild("days") days: OIntegerInputComponent
  @ViewChild("beginDate") beginDate: ODateInputComponent
  @ViewChild("endDate") endDate: ODateInputComponent
  constructor(@Inject(MAT_DIALOG_DATA) public data: any){
    console.log('Datos recibidos:', data);
  }
  getMinDate(){
    const currentDate = new Date()
    return new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
  }
  onBeginDateChanged(){
    if (this.beginDate.getValue()){
      const date = new Date(this.beginDate.getValueAsDate())
      this.endDate.setValue(new Date(date.getFullYear(), date.getMonth(), date.getDate() + Number.parseInt(this.days.getValue())));
    } else {
      this.endDate.setValue(null)
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
