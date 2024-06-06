import { Component, Injector } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NavigationService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-manage-all-routes',
  templateUrl: './manage-all-routes.component.html',
  styleUrls: ['./manage-all-routes.component.css']
})
export class ManageAllRoutesComponent {

  constructor(protected sanitizer: DomSanitizer,
    private router: Router,
    protected injector: Injector){
      this.injector.get(NavigationService).initialize();
  }


  public getImageSrc(base64: any): any {
    const image_code = base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
    return image_code
  }

onClicEdit(route_id: any) {
  this.router.navigate(['main','route-manage',route_id])
}

onClicDelete(route_id: any) {
  this.router.navigate(['main','route-manage',route_id])
}

}
