import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { OGridComponent, OntimizeService } from 'ontimize-web-ngx';
import { Router } from '@angular/router';

@Component({
  selector: 'app-business-home',
  templateUrl: './pack-home.component.html',
  styleUrls: ['./pack-home.component.css']
})
export class PackHomeComponent {
  public showWaitForLongTask = false;

  constructor(
    private ontimizeService: OntimizeService,
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer,
    private router: Router,
  ) {
    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration("businesses"));
  }
  ngOnInit() {
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }

  public openDetail(data: any): void {
    this.router.navigate(['main/businesses/' + data.bsn_id]);
  }

  truncateName(name: string): string {
    if (name.length > 25) {
        return name.substr(0, 25) + '...';
    } else {
        return name;
    }
}

}
