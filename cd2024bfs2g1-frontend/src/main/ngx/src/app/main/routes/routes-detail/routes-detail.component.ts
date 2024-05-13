import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { LandmarksService } from '../../landmarks.service';
import { OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-routes-detail',
  templateUrl: './routes-detail.component.html',
  styleUrls: ['./routes-detail.component.css']
})
export class RoutesDetailComponent implements OnInit{

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private ontimizeService: OntimizeService,
    protected sanitizer: DomSanitizer,
    protected dialog: MatDialog,
    protected landmarkService: LandmarksService
  ) {
    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration("landmarks"));
   }

  ngOnInit(){
  }



  public convertTime(minutos: number):  string {
    
    const horas = Math.floor(minutos / 60);
    const minutosRestantes = minutos % 60;
    if(horas == 0 && minutosRestantes != 0){
      return minutosRestantes + "min";
    }else if(horas != 0 && minutosRestantes == 0){
      return horas + "h ";
    }else{
       return horas + "h " + minutosRestantes + "min";
    }
   
  
  }

}

