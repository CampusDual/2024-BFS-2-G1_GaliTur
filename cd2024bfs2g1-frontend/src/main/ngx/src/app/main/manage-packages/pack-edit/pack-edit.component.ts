import { AfterViewInit, Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-pack-edit',
  templateUrl: './pack-edit.component.html',
  styleUrls: ['./pack-edit.component.css']
})
export class PackEditComponent implements AfterViewInit{
imgId: any;

  constructor(private router: Router,private activeRoute: ActivatedRoute){
  }
  ngAfterViewInit(): void {
    this.pck_id = this.getPackId()
  }

   onAddRtsOrBss(){
      this.router.navigate(["main", "packs","new",this.pck_id]);
      /*otra forma para probar this.router.navigate(['main','routes', 'new', $event['route_id']])*/
  }
  pck_id: any =""

  getPackId():number{
    return +this.activeRoute.snapshot.params["pck_id"];
  }
}




