import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-manage-all-routes',
  templateUrl: './manage-all-routes.component.html',
  styleUrls: ['./manage-all-routes.component.css']
})
export class ManageAllRoutesComponent {

  constructor(protected sanitizer: DomSanitizer
  ){
  }


  public getImageSrc(base64: any): any {
    const image_code = base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
    return image_code
  }

onClicEdit(arg0: any) {
throw new Error('Method not implemented.');
}

}
