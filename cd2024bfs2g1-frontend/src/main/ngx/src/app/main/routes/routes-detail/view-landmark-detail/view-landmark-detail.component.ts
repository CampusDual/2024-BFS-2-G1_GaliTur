import { Component, OnInit, ViewChild } from '@angular/core';
import { OMapComponent } from 'ontimize-web-ngx-map';

@Component({
  selector: 'app-view-landmark-detail',
  templateUrl: './view-landmark-detail.component.html',
  styleUrls: ['./view-landmark-detail.component.css']
})
export class ViewLandmarkDetailComponent {
  _eventsArray: Array<any> = [];

  @ViewChild('oMap')
  protected oMap: OMapComponent;
data: any;

  constructor() { }


  ngAfterViewInit() {
    this.getDrawLayer();
  }

  getDrawLayer() {
    console.log(this.oMap.getMapService().getDrawLayer());
  }

  addDrawEvent(arg) {
    this._eventsArray.push(arg);
  }

  get eventsArray(): Array<any> {
    return this._eventsArray;
  }

  set eventsArray(arg: Array<any>) {
    this._eventsArray = arg;
  }

}
