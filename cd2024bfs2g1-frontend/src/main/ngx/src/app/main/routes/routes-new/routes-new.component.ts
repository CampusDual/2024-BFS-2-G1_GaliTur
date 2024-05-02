import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-routes-new',
  templateUrl: './routes-new.component.html',
  styleUrls: ['./routes-new.component.css']
})
export class RoutesNewComponent {

  constructor(private router:Router){}

  onClickOk($event:Event){
    this.router.navigate(['../'])
   
  }
}
