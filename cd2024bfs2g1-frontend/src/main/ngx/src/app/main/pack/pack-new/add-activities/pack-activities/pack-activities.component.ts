import { Component, Injector } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { AddActivitiesComponent } from '../add-activities.component';
import { OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-pack-activities',
  templateUrl: './pack-activities.component.html',
  styleUrls: ['./pack-activities.component.css']
})
export class PackActivitiesComponent {


  protected service: OntimizeService;
  public arrayDias = []
  

  constructor(
    protected sanitizer: DomSanitizer,
    private dialogRef: MatDialogRef<PackActivitiesComponent>,
    private activeRoute: ActivatedRoute,
    protected injector: Injector
  ){
    this.service = this.injector.get(OntimizeService);
  }



  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }

  truncateName(name: string): string {
    if (name.length > 40) {
      return name.substr(0, 40) + "...";
    } else {
      return name;
    }
  }

  addRow(): any {
    console.log("ey")
    }

  public goBack(): void {
    this.dialogRef.close()
  }

  protected configureService() {
    // Configure the service using the configuration defined in the `app.services.config.ts` file
    const conf = this.service.getDefaultServiceConfiguration('packs');
    this.service.configureService(conf);
  }

  ngOnInit(): void {
    
    console.log('Al emergente le llego el id: ' + AddActivitiesComponent.packId);
    this.configureService();
    this.getDays();
  }


  getDays() {
      const filter = {
        'pck_id': AddActivitiesComponent.packId
      };
      const columns = ['pck_name', 'pck_days'];
      this.service.query(filter, columns, 'packDays').subscribe(resp => {
        if (resp.code === 0) {
  
          // resp.data contains the data retrieved from the server

          const array = resp.data;
          const data = array[0];
          const days = data['pck_days'];

          for(let d of days){
            this.arrayDias.push({"day":d['day'],"day_string":d['day_string']})
          }     

        } else {
          alert('Impossible to query data!');
        }
      });
    }

  



}
