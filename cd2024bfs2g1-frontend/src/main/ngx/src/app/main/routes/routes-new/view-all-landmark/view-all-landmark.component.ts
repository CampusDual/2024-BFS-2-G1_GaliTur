import { AfterViewInit, Component} from '@angular/core';
import { Expression, FilterExpressionUtils, OntimizeService, Util } from 'ontimize-web-ngx';
import { Landmark } from './landmark-model';
import { RouteService } from 'src/app/shared/services/route.service';

@Component({
  selector: 'app-view-all-landmark',
  templateUrl: './view-all-landmark.component.html',
  styleUrls: ['./view-all-landmark.component.css']
})
export class ViewAllLandmarkComponent implements AfterViewInit{

  
  datosTabla: Landmark[] = [];

  protected configureService() {
    const conf = this.ontimizeService.getDefaultServiceConfiguration('landmarks');
    this.ontimizeService.configureService(conf);
  }

  constructor(private ontimizeService: OntimizeService,private routeService:RouteService) { 
    this.configureService()
  }
  ngAfterViewInit(): void {
    const routeId=this.routeService.getActualRouteId()
    this.consultarDatosPorId(routeId)
    console.log('Mostando el id ' + routeId)
  }

  consultarDatosPorId(id: any): void {
    this.ontimizeService.query({landmark_id:id},['landmark_id','name'],'landmark').subscribe((response) => {
      this.datosTabla.push(...response.data);
      console.log(this.datosTabla)
    });
  }

  // consultarDatosDirectamente(){
  //   this.ontimizeService.query({
  //     sql: this.query
  //   }).subscribe((response) => {
  //     if (response && response.data) {
  //       this.datosTabla = response.data;
  //     } else {
  //       this.datosTabla = [];
  //     }
  //   });
  // }


  createFilter(values: Array<{ attr; value }>): Expression {
    let filters: Array<Expression> = [];
    values.forEach(fil => {
      if (Util.isDefined(fil.value)) {
        if (
          fil.attr === "name" ||
          fil.attr === "description" ||
          fil.attr === "opening_time" ||
          fil.attr === "closing_time" ||
          fil.attr === "coordinates" 
        ) {
          filters.push(
            FilterExpressionUtils.buildExpressionLike(
              "name" + fil.attr,
              fil.value
            )
          );
        }

      }
    });

    if (filters.length > 0) {
      return filters.reduce((exp1, exp2) =>
        FilterExpressionUtils.buildComplexExpression(
          exp1,
          exp2,
          FilterExpressionUtils.OP_AND
        )
      );
    } else {
      return null;
    }
  }

}
