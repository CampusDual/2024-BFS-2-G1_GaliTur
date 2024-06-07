import { Component, Injector } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { DialogService, NavigationService, OntimizeService } from 'ontimize-web-ngx';
import { RouteModel } from '../../routes/route.model';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ShowPacksConfirmDeleteComponent } from './show-packs-confirm-delete/show-packs-confirm-delete.component';

@Component({
  selector: 'app-manage-all-routes',
  templateUrl: './manage-all-routes.component.html',
  styleUrls: ['./manage-all-routes.component.css']
})
export class ManageAllRoutesComponent {

  constructor(protected sanitizer: DomSanitizer,
    private router: Router,
    protected injector: Injector,
    private dialogService: DialogService,
    private dialog: MatDialog,
    private ontimizeRouteService: OntimizeService,){
      this.injector.get(NavigationService).initialize();
  }
  packsWithThisRoute = []
  protected configureRouteService() {
    const conf =
      this.ontimizeRouteService.getDefaultServiceConfiguration("routes");
    this.ontimizeRouteService.configureService(conf);
  }

  confirmAction(mensaje: string):boolean {
    let userResponse: boolean = false
    const dialogRef = this.dialog.open(ShowPacksConfirmDeleteComponent,{data:{mensaje:mensaje,packs:this.packsWithThisRoute}});
    dialogRef.afterClosed().subscribe((result)=>{
      console.log("El resultado es ", result)
      userResponse = result
    })
    return userResponse
  }
  

  public getImageSrc(base64: any): any {
    const image_code = base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
    return image_code
  }

onClicEdit(route_id: any) {
  this.router.navigate(['main','route-manage',route_id])
}

onClicDelete(route_id:any) {
  if(this.canSafeDelete(route_id)){
    this.deleteRoute(route_id)
  }else{
    console.log("No se puede borrar porque tiene estos packs ", this.packsWithThisRoute)
    if(this.confirmAction("DELETE_ROUTE_CONFIRM_MESSAGE")){

    }
    
  }
  this.packsWithThisRoute = []
}
//safe delete
//ESTA MIERDA NO ESTA DEVOLVIENDO BIEN LOS DATOS
//REVISALA ANDA <----------------
//BUEN FINDE
private canSafeDelete(route_id:any):boolean{
  let safeDelete = false
  this.configureRouteService()
  this.ontimizeRouteService.query({route_id:route_id},['p.pck_name'],'searchPacks').subscribe((response)=>{
    if(response.data){
      console.log('Hay response data')
      safeDelete = false
      this.packsWithThisRoute.push(...response.data)
    }
    else{
      console.log('No hay response data')
      safeDelete = true
    }
  })

  console.log("packsWithThisRoute antes del return: ", this.packsWithThisRoute)
  return safeDelete

}

private deleteRoute(route_id:any){
  this.ontimizeRouteService.delete({route_id:route_id},'route').subscribe((updateResponse)=>{
  })
}
//????????????
showConfirm(evt: any) {
  if (this.dialogService) {
    this.dialogService.confirm('Confirm dialog title', 'Do you really want to accept?')
  }
}

}
