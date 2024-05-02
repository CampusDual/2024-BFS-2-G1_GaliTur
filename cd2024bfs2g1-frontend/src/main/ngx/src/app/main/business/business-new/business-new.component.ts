import { Component, ViewChild } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { OCheckboxComponent, OCurrencyInputComponent } from 'ontimize-web-ngx';


@Component({
  selector: 'app-business-new',
  templateUrl: './business-new.component.html',
  styleUrls: ['./business-new.component.css']
})
export class BusinessNewComponent {


  selectedOption: number;
  cifDniControl = new FormControl('',[Validators.required, this.cifDniValidator]);

  
  public switchDestinationState: boolean = false;
  @ViewChild('switchDestination', { static: false }) switchDestination: OCheckboxComponent;
  @ViewChild('currency1', { static: false }) currency1: OCurrencyInputComponent;
  getSwitchValue(){
        this.switchDestinationState = this.switchDestination.getValue();
        this.currency1.setValue(null);

   }


   public switchDestinationState2: boolean = false;
   @ViewChild('switchDestination2', { static: false }) switchDestination2: OCheckboxComponent;
   @ViewChild('currency2', { static: false }) currency2: OCurrencyInputComponent;
   getSwitchValue2(){
         this.switchDestinationState2 = this.switchDestination2.getValue();
         this.currency2.setValue(null);
 
    }

    public switchDestinationState3: boolean = false;
    @ViewChild('switchDestination3', { static: false }) switchDestination3: OCheckboxComponent;
    @ViewChild('currency3', { static: false }) currency3: OCurrencyInputComponent;
    getSwitchValue3(){
          this.switchDestinationState3 = this.switchDestination3.getValue();
          this.currency3.setValue(null);
  
     }









setSelectedOption($event: any) {
  this.selectedOption = $event

}



cifDniValidator(control: FormControl) {
    const value = control.value;
    const cifRegex = /^([A-Z])(\d{8})$/; // Expresión regular para CIF
    const dniRegex = /^(\d{8}[A-Za-z])$/; // Expresión regular para DNI

    if (cifRegex.test(value) || dniRegex.test(value)) {
      return null; // Válido
    } else {
      return { cifDniInvalid: true }; // Inválido
    }

}





  getDataArray() :any{
    const array: Array<Object> = [];
    array.push({
      'key': 1,
      'value': 'Restaurant'
    });
    array.push({
      'key': 2,
      'value': 'Lodging'
    });
    array.push({
      'key': 3,
      'value': 'Agency guide'
    });
    return array;
  }

  getValue() {
    return 1;
  }



  



  
  
  

}
