
import { Injectable } from "@angular/core";
import { OntimizeService } from "ontimize-web-ngx";
import { RouteModel } from "src/app/main/routes/route.model";

@Injectable()
export class RouteService{
    constructor(private ontimizeService: OntimizeService) { 
        //clave valor
        //columans
        //entidad
        this.configureService()
      }

    private actualRoute:RouteModel

    protected configureService() {
        const conf = this.ontimizeService.getDefaultServiceConfiguration('routes');
        this.ontimizeService.configureService(conf);
    }
    
    setActualRoute(name:string,description:string){
        this.ontimizeService.query({name:name,description:description},
            ['route_id','name','description','estimated_duration','difficulty'],'route').subscribe((response) => {
            this.actualRoute= response.data[0]
            console.log('Enviando a landmarks el id '+ this.actualRoute.route_id)
         });
    }
    
    getActualRouteId():number{
        return this.actualRoute.route_id
    }

    
}