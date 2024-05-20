import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { OMapComponent } from 'ontimize-web-ngx-map';
import { OMapBase } from 'ontimize-web-ngx-map/lib/components/map/o-map-base.class';

@Component({
  selector: 'app-view-landmark-detail',
  templateUrl: './view-landmark-detail.component.html',
  styleUrls: ['./view-landmark-detail.component.css']
})
export class ViewLandmarkDetailComponent {
  _eventsArray: Array<any> = [];
  @ViewChild('oMapMarker') protected oMap: OMapComponent;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
  private dialogRef: MatDialogRef<ViewLandmarkDetailComponent>
) { }


  ngAfterViewInit() {
    this.oMap.addMarker("1","42.940599","-7.120727",{},true,false,true,"1")
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

  public backToDetail(data: any): void {
    this.dialogRef.close()
  }

  async delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  async zoomPointlandmark(data: any): Promise<void> {
    this.oMap.setCenter(data.coordinates)
    await this.delay(300);
    this.oMap.getMapService().setZoom(11)
  }

}
