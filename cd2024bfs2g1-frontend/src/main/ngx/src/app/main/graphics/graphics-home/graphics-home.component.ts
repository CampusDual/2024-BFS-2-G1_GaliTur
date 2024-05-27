import { Component, OnInit } from '@angular/core';
import { OTranslateService, OntimizeService } from 'ontimize-web-ngx';
import { PieChartConfiguration } from 'ontimize-web-ngx-charts';


@Component({
  selector: 'app-graphics-home',
  templateUrl: './graphics-home.component.html',
  styleUrls: ['./graphics-home.component.css']
})

export class GraphicsHomeComponent implements OnInit {


  constructor(private ontimizeService: OntimizeService) {  
      this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration("packs"));  
  }

  ngOnInit() {}
}
