import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PackActivitiesComponent } from './pack-activities/pack-activities.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-activities',
  templateUrl: './add-activities.component.html',
  styleUrls: ['./add-activities.component.css']
})
export class AddActivitiesComponent {

  static packId: any;

  constructor(protected dialog: MatDialog, private activeRoute: ActivatedRoute){

  }

  addActivity() {
    
     
    this.dialog.open(PackActivitiesComponent, {
      width: '1200px',
      maxHeight: '600px',
      minHeight: '600px',
      maxWidth: '80vw'

    });
}

ngOnInit(): void {
  AddActivitiesComponent.packId=this.activeRoute.snapshot.params['pck_id']
  console.log('Al pack le llego el id: ' + AddActivitiesComponent.packId)
}

}
