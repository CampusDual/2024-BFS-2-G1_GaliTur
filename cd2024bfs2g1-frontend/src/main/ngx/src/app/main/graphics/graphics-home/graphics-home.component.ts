import { Component, OnInit } from '@angular/core';
import { OTranslateService, OntimizeService } from 'ontimize-web-ngx';


@Component({
  selector: 'app-graphics-home',
  templateUrl: './graphics-home.component.html',
  styleUrls: ['./graphics-home.component.css']
})

export class GraphicsHomeComponent implements OnInit {

  colors = {domain: ["#84b463", "#9cc77f", "#bde0a5", "#def9cc"]}


  constructor(private ontimizeService: OntimizeService) {  
      this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration("packs"));  
  }

  ngOnInit() {}

}
