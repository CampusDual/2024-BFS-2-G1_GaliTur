import { HttpClient, HttpParams } from '@angular/common/http';
import { AfterViewInit, Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NavigationService, Observable, OntimizeService } from 'ontimize-web-ngx';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-manage-all-packs',
  templateUrl: './manage-all-packs.component.html',
  styleUrls: ['./manage-all-packs.component.css']
})
export class ManageAllPacksComponent{
  constructor(private router:Router,
    protected sanitizer: DomSanitizer,
    private ontimizePackService: OntimizeService,
    private http: HttpClient,
    protected injector: Injector,
  ){
    this.configurePackImgService()
    this.injector.get(NavigationService).initialize();
  }

  private baseUrl = 'http://localhost:8080/packs';


  public getImageSrc(base64: any): any {
    const image_code = base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
    return image_code
  }

  getImage(img_id:any){
    console.log("Data: " + this.aux.img_id)
  }
  aux:any = {}
  idImagen:any = []
  idImagen2:any = []
  protected configurePackImgService() {
    const confPack =
      this.ontimizePackService.getDefaultServiceConfiguration("packs");
    this.ontimizePackService.configureService(confPack);
  }

  // protected configureImageService() {
  //   const confImg =
  //     this.ontimizeImageService.getDefaultServiceConfiguration("images");
  //   this.ontimizeImageService.configureService(confImg);
  // }


  
onClicDelete(pck_id: any) {
  // if (confirm('¿Estás seguro de que deseas eliminar este paquete?')) {
  //   this.ontimizePackService.delete({pck_id:pck_id},"pack").pipe(
  //     catchError(error => {
  //       console.error('Error al eliminar el paquete:', error);
  //       alert('Hubo un error al eliminar el paquete. Por favor, intenta nuevamente.');
  //       return of(null); // Devuelve un Observable vacío para manejar el error
  //     })
  //   ).subscribe(response => {
  //     if (response) {
  //       alert('Paquete eliminado exitosamente.');
  //     }
  //   });
  // }
}

// getImageIdAux(pck_id:any):any{
//   this.ontimizePackService
//     .query(
//       {pck_id:pck_id},
//       ["ip.img_id","p.pck_id","i.img_code"],
//       "packDetail"
//     )
//     .subscribe((response) => {
//       this.idImagen.push(...response.data)
//       this.aux = this.idImagen[0]
//       console.log("Id de la imagen y pck" + this.aux.img_id,"   ",this.aux.img_code)
//     });
// }

onClicEdit(pck_id: any) {
  this.router.navigate(['main','pack-manage',pck_id])
}

getPacks(page: number, limit: number): Observable<any> {
  let params = new HttpParams().set('page', page.toString()).set('limit', limit.toString());
  return this.http.get<any>(`${this.baseUrl}/advancedsearch`, { params });
}

}
