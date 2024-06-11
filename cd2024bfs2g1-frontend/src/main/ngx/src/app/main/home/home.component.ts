import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router, NavigationExtras } from '@angular/router';
import { OGridComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements AfterViewInit {
  @ViewChild('popularsPackGrid', { static: false }) popularsPackGrid: OGridComponent;
  packsWithRank: any[] = [];

  constructor(
    protected sanitizer: DomSanitizer,
    private router: Router,
    private actRoute: ActivatedRoute,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {}

  ngAfterViewInit() {
    // Calcular posicion cuando los datos esten disponibles
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



  truncateInfo(name: string): string {
    return name.length > 10 ? name.substr(0, 10) + "..." : name;
  }

  calculatePackRanks() {
    const dataArray = this.popularsPackGrid.dataArray;
    this.packsWithRank = dataArray
      .map((pack, index) => ({ ...pack, rank: index + 1 }));
  }

  //Metodo para redirect dinamico de packs
  openDetailPack(data: any): void {
    const currentUrl = this.router.url; // Capturar la URL actual
    const navigationExtras: NavigationExtras = {
      state: { previousUrl: currentUrl },
      relativeTo: this.route // Enviar la URL actual como navigation state
    };
    this.router.navigate(['../main/packs/' + data.pck_id], navigationExtras);
  }

  

  openPopularPacks() {
    const visibleGrid = document.getElementById('visibleGrid');
    visibleGrid.scrollIntoView({ behavior: 'smooth' });
  }
}