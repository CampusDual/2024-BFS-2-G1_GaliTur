import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { RouteModel } from '../../route.model';
import { RouteService } from 'src/app/shared/services/route.service';
import { FormValueOptions, OTextInputComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-add-landmark',
  templateUrl: './add-landmark.component.html',
  styleUrls: ['./add-landmark.component.css']
})
export class AddLandmarkComponent implements OnInit,AfterViewInit{
  
onChangeValue() {
  this.routeIdInput.setData(this.routeId)
}

  @ViewChild('routeIdInput') routeIdInput: OTextInputComponent

  routeId:number = 10

  constructor(private routeService:RouteService){}
  ngAfterViewInit(): void {
    this.routeIdInput.setData(this.routeId)
    // this.routeIdInput.setData(this.routeId)
    // console.log(this.routeId)
    ///////////////////////
    // if (this.routeIdInput) {
    //   const options: FormValueOptions = {
    //     emitModelToViewChange: true, 
    //     emitViewToModelChange: false, 
    //     emitModelToViewValueChange: true 
    //   };
    //   this.routeIdInput.setValue(this.routeId.toString(), options);
    // }
    ///////////////////////
    
  }

  ngOnInit(): void {
    this.routeId=this.routeService.getActualRouteId()
    console.log('Al addLandmark le llego el id: ' + this.routeId)
    this.routeIdInput.setData('pito')
  }


}
