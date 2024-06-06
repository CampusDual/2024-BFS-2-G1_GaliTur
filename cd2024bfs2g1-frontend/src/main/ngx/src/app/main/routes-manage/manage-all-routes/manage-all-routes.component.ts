import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-all-routes',
  templateUrl: './manage-all-routes.component.html',
  styleUrls: ['./manage-all-routes.component.css']
})
export class ManageAllRoutesComponent {

  constructor(protected sanitizer: DomSanitizer,
    private router: Router){
  }


  public getImageSrc(base64: any): any {
    const image_code = base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
    return image_code
  }

onClicEdit(route_id: any) {
  this.router.navigate(['main','route-manage',route_id])
}

}
