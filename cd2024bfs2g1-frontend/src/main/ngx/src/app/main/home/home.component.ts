import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(
    private router: Router,
    private actRoute: ActivatedRoute
  ) {
  }

  ngOnInit() {
  }

  navigate() {
    this.router.navigate(['../', 'login'], { relativeTo: this.actRoute });
  }
  
  public openPacks(): void {
    this.router.navigate(['main/packs']);
  }

  public getLogoImageSrc(): any {
    return "./assets/images/logo-sidebar.png";
  }

  public getContentImageSrc(): any {
    return "./assets/images/home-image.jpeg";
  }
}
