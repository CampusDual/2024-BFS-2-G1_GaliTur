import { HttpClient, HttpParams } from '@angular/common/http';
import { AfterViewInit, Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NavigationService, Observable, OntimizeService } from 'ontimize-web-ngx';
import { catchError, of } from 'rxjs';
import { CONFIG } from 'src/app/app.config';

@Component({
  selector: 'app-manage-all-packs',
  templateUrl: './manage-all-packs.component.html',
  styleUrls: ['./manage-all-packs.component.css']
})
export class ManageAllPacksComponent{

  private baseUrl = CONFIG.apiEndpoint;
  aux:any = {}
  idImagen:any = []
  idImagen2:any = []


  constructor(private router:Router,
    protected sanitizer: DomSanitizer,
    private ontimizePackService: OntimizeService,
    private ontimizeImageService: OntimizeService,
    private ontimizeImagePackService: OntimizeService,
    private http: HttpClient,
    protected injector: Injector,
  ){
    this.configurePackService()
    this.configureImageService() 
    this.configurePackImageService() 
    this.injector.get(NavigationService).initialize();
  }

  public getImageSrc(base64: any): any {
    const image_code = base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
    return image_code
  }

  protected configurePackService() {
    const confPack =
      this.ontimizePackService.getDefaultServiceConfiguration("packs");
    this.ontimizePackService.configureService(confPack);
  }
  protected configureImageService() {
    const confPack =
      this.ontimizeImageService.getDefaultServiceConfiguration("images");
    this.ontimizeImageService.configureService(confPack);
  }
  protected configurePackImageService() {
    const confPack =
      this.ontimizeImagePackService.getDefaultServiceConfiguration("imagePacks");
    this.ontimizeImagePackService.configureService(confPack);
  }

//TODO Boton de borrar, no se puede usar por restricciones, base de datos no en CASCADA <------------------------

// onClicDelete(pck_id: any,image_id:any,imp_ip:any) {
//   console.log("Pack id ",pck_id," image id ",image_id," imagepack id ",imp_ip)
//   this.onPackImageDelete(imp_ip)
//   this.onImgDelete(image_id)
//   this.onPackDelete(pck_id)
// }
// private onPackImageDelete(imp_ip:any){
//   this.ontimizeImagePackService.delete({imp_ip:imp_ip},"imagePack").subscribe(response => {
//     if (response) {
//       console.log(response.data)
//     }
//   });
// }
// private onImgDelete(image_id:any){
//   this.ontimizeImageService.delete({image_id:image_id},"image").subscribe(response => {
//     if (response) {
//       console.log(response.data)
//     }
//   });
// }
// private onPackDelete(pck_id:any){
//   if (confirm('¿Estás seguro de que deseas eliminar este paquete?')) {
//     this.ontimizePackService.delete({pck_id:pck_id},"pack").pipe(
//       catchError(error => {
//         console.error('Error al eliminar el paquete:', error);
//         alert('Hubo un error al eliminar el paquete. Por favor, intenta nuevamente.');
//         return of(null); // Devuelve un Observable vacío para manejar el error
//       })
//     ).subscribe(response => {
//       if (response) {
//         alert('Paquete eliminado exitosamente.');
//       }
//     });
//   }
// }

onClicEdit(pck_id: any) {
  this.router.navigate(['main','pack-manage',pck_id])
}

onClicDelete(pck_id: any) {
  
}

getPacks(page: number, limit: number): Observable<any> {
  let params = new HttpParams().set('page', page.toString()).set('limit', limit.toString());
  return this.http.get<any>(`${this.baseUrl}/advancedsearch`, { params });
}

}
