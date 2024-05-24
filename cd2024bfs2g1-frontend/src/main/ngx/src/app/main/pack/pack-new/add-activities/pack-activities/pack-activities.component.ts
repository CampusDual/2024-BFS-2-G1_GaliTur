import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-pack-activities',
  templateUrl: './pack-activities.component.html',
  styleUrls: ['./pack-activities.component.css']
})
export class PackActivitiesComponent {
  

  constructor(
    protected sanitizer: DomSanitizer
  ){
    
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



}
