import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PackActivitiesComponent } from './pack-activities/pack-activities.component';

@Component({
  selector: 'app-add-activities',
  templateUrl: './add-activities.component.html',
  styleUrls: ['./add-activities.component.css']
})
export class AddActivitiesComponent {

  constructor(protected dialog: MatDialog){

  }

  addActivity() {
    
     
    this.dialog.open(PackActivitiesComponent, {
      height: '600px',
      width: '1200px',
    });


}

}
