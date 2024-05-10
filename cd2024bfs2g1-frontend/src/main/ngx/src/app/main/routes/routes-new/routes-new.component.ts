import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OSlideToggleComponent, OTextInputComponent, OTextareaInputComponent } from 'ontimize-web-ngx';
import { RouteService } from 'src/app/shared/services/route.service';
import { RouteModel } from '../route.model';

@Component({
  selector: 'app-routes-new',
  templateUrl: './routes-new.component.html',
  styleUrls: ['./routes-new.component.css']
})
export class RoutesNewComponent{
onTest() {
  this.router.navigate(['ok'],{relativeTo:this.route})
}

  @ViewChild("nameInput") nameInput: OTextInputComponent;
  @ViewChild("descriptionInput") descriptionInput: OTextareaInputComponent;


  constructor(private router:Router,private routeService:RouteService,private route: ActivatedRoute){
  }

  show= false
  isChecked = true

  getValue(): any {
  return true
  }
  
  onClickOk(){
    this.routeService.setActualRoute(this.nameInput.getValue(),this.descriptionInput.getValue())
    this.router.navigate(['ok'],{relativeTo:this.route})
    
  }
  
}
