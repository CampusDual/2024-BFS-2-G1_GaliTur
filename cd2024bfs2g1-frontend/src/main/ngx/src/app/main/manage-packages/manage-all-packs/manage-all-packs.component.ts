import { AfterViewInit, Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-manage-all-packs',
  templateUrl: './manage-all-packs.component.html',
  styleUrls: ['./manage-all-packs.component.css']
})
export class ManageAllPacksComponent implements AfterViewInit{
  constructor(private router:Router,
    protected sanitizer: DomSanitizer,
    private ontimizePackService: OntimizeService,
    // private ontimizeImageService: OntimizeService
  ){
    this.configurePackImgService()
    // this.configureImageService()
  }
  ngAfterViewInit(): void {
     this.getImageIdAux(104)
  }
  
  getImageIdAux(pck_id:any):any{
    // this.ontimizePackService
    //   .query(
    //     {pck_id:pck_id},
    //     ["ip.img_id","p.pck_id","i.img_code"],
    //     "packDetail"
    //   )
    //   .subscribe((response) => {
    //     this.idImagen.push(...response.data)
    //     this.aux = this.idImagen[0]
    //     console.log("Id de la imagen y pck" + this.aux.img_id,"   ",this.aux.img_code)
    //   });
  }

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

}
onClicEdit(pck_id: any) {
  this.router.navigate(['main','pack-manage',pck_id])
}

}

