import { Component, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { OGridComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  @ViewChild('popularsPackGrid', { static: true }) popularsPackGrid: OGridComponent;
  packsWithRank: any[] = [];

  constructor(
    protected sanitizer: DomSanitizer,
    private router: Router,
    private actRoute: ActivatedRoute
  ) {}

  ngOnInit() {}

  ngAfterViewInit() {
    // Calcular posicion cuand los datos esten disponibles
    this.popularsPackGrid.onDataLoaded.subscribe(() => {
      this.calculatePackRanks();
    });
  }

  navigate() {
    this.router.navigate(['../', 'login'], { relativeTo: this.actRoute });
  }

  public openPacks(): void {
    this.router.navigate(['../packs'], { relativeTo: this.actRoute });
  }

  public getLogoImageSrc(): any {
    return "./assets/images/logo-sidebar.png";
  }

  public getContentImageSrc(): any {
    return "./assets/images/home-image.jpeg";
  }

  public openDetail(data: any): void {
    this.router.navigate(['packs/' + data.pck_id]);
  }

  public getImageSrc(base64: any): any {
    return base64
      ? this.sanitizer.bypassSecurityTrustResourceUrl(
          "data:image/*;base64," + base64
        )
      : "./assets/images/no-image-transparent.png";
  }

  truncateName(name: string): string {
    return name.length > 25 ? name.substr(0, 25) + "..." : name;
  }

  truncateInfo(name: string): string {
    return name.length > 10 ? name.substr(0, 10) + "..." : name;
  }

  calculatePackRanks() {
    const dataArray = this.popularsPackGrid.dataArray;
    this.packsWithRank = dataArray
      .map((pack, index) => ({ ...pack, rank: index + 1 }))
  }
}
