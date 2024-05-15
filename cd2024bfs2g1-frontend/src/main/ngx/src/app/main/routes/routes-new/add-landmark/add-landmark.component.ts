import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { RouteModel } from '../../route.model';
import { RouteService } from 'src/app/shared/services/route.service';
import { FormValueOptions, OFormComponent, OTextInputComponent } from 'ontimize-web-ngx';
import { ActivatedRoute, Router } from '@angular/router';
import { OMapComponent, OMarkerComponent } from 'ontimize-web-ngx-map';

@Component({
  selector: 'app-add-landmark',
  templateUrl: './add-landmark.component.html',
  styleUrls: ['./add-landmark.component.css']
})
export class AddLandmarkComponent implements OnInit,AfterViewInit{


  @ViewChild('routeIdInput') routeIdInput: OTextInputComponent
  @ViewChild('oMarker') marker: OMarkerComponent
  @ViewChild('landmarksNew') landmarksNew: OFormComponent

  routeId:number
  _eventsArray: Array<any> = [];


  @ViewChild('oMap')

  protected oMap: OMapComponent;

  constructor(private router: Router, private route: ActivatedRoute,
     private activeRoute:ActivatedRoute){}

  ngAfterViewInit(): void {
    this.routeIdInput.setData(this.routeId)
  }

  ngOnInit(): void {
    this.routeId=this.activeRoute.snapshot.params['route_id']
    console.log('Al addLandmark le llego el id: ' + this.routeId)
  }

  onChangeValue() {
    this.routeIdInput.setData(this.routeId)
  }

  onClickOk(){
    this.router.navigate(['../'],{relativeTo:this.route})
  }

  onClickMap(e){
    this.oMap.addMarker(1,e.latlng.lat,e.latlng.lng,{},{},false,{},{})
    this.landmarksNew.setFieldValues({"coordinates": e.latlng.lat+","+e.latlng.lng})
  }
  
}
