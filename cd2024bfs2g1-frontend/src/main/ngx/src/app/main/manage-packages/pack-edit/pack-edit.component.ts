import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-pack-edit',
  templateUrl: './pack-edit.component.html',
  styleUrls: ['./pack-edit.component.css']
})
export class PackEditComponent {

  constructor(private ontimizeManagePackService: OntimizeService,
    private ontimizeManagePackImageService: OntimizeService,
    private activeRoute: ActivatedRoute
  ){
    this.idPack = this.getPackId()
    this.configurePackImageService()
     this. consultarDatosPackImage(this.idPack)
     console.log("El id de la ruta es:" + this.idPack)

  }

  dataPack:any= []
  idPack:number=0
  imgId=""


  consultarDatosPackImage(id:number){
    this.ontimizeManagePackImageService
      .query(
        {pck_id:id},
        ["img_id"],
        "imagePack"
      )
      .subscribe((response) => {
        this.dataPack.push(...response.data);
        console.log(this.dataPack[0].img_id);
        this.imgId=this.dataPack[0].img_id
      });
  }
  protected configurePackImageService() {
    const conf =
      this.ontimizeManagePackImageService.getDefaultServiceConfiguration("imagePacks");
    this.ontimizeManagePackImageService.configureService(conf);
  }

  getPackId():number{
    return +this.activeRoute.snapshot.params["pck_id"];
  }
  }


