import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-pack-client',
  templateUrl: './pack-client.component.html',
  styleUrls: ['./pack-client.component.css']
})
export class PackClientComponent {

  constructor(
    private ontimizeService: OntimizeService,
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private router: Router,
  ) {
    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration("packs"));
  }

  public openDetail(data: any): void {
    this.router.navigate(['main/packs/pack-client/' + data.pck_id]);
  }

}
