import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { GalleryImage, GalleryOptions } from 'ontimize-web-ngx-gallery';
import { GalleryImageSize } from 'ontimize-web-ngx-gallery';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'app-business-new',
  templateUrl: './business-new.component.html',
  styleUrls: ['./business-new.component.css']
})
export class BusinessNewComponent {


setSelectedOption($event: any) {
  this.selectedOption = $event

}

cifDniControl = new FormControl('',[Validators.required, this.cifDniValidator]);


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



public galleryOptions: GalleryOptions[];
public galleryImages: GalleryImage[];
selectedOption: number;

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



  


  ngOnInit(): void {
  


  }

  
  
  

}
