import { Component, Injector } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NavigationService, OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-manage-all-routes',
  templateUrl: './manage-all-routes.component.html',
  styleUrls: ['./manage-all-routes.component.css']
})
export class ManageAllRoutesComponent {

  constructor(
    protected sanitizer: DomSanitizer,
    protected injector: Injector,
    private router: Router,
    private ontimizeRouteService:OntimizeService){
      this.injector.get(NavigationService).initialize();
  }


  public getImageSrc(base64: any): any {
    const image_code = base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
    return image_code
  }

  onClicDelete(route_id: any) {
    console.log(route_id)
    //this.deleteRouteQuery(route_id)
  }

  onClicEdit(route_id: any) {
    this.router.navigate(['main','route-manage',route_id])
  }

  // deleteRouteQuery(route_id: any): void {
  //   const conf =
  //   this.ontimizeRouteService.getDefaultServiceConfiguration("routes");
  //   this.ontimizeRouteService.configureService(conf);
  //   this.ontimizeRouteService
  //     .delete(
  //       { route_id: route_id },
  //       "route"
  //     )
  //     .subscribe((response) => {
  //       console.log("Se elimino la ruta: ", response)
  //     });
  // }

}
