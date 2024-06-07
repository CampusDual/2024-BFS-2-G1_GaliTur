import { Component, Inject, Renderer2, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-pack-valoration',
  templateUrl: './pack-valoration.component.html',
  styleUrls: ['./pack-valoration.component.css']
})
export class PackValorationComponent {
  @ViewChild('star2') star2: MatIcon;
  @ViewChild('star3') star3: MatIcon;
  @ViewChild('star4') star4: MatIcon;
  @ViewChild('star5') star5: MatIcon;
  
  stars :number;


  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private renderer: Renderer2, 
    private router: Router,
    protected sanitizer: DomSanitizer, 
    private actRoute: ActivatedRoute, 
    private dialogRef: MatDialogRef<PackValorationComponent>){
    this.stars = data.stars
  }


  valorateSelect(stars: number):void{
    this.star2._elementRef.nativeElement.classList.remove('star_color')
    this.star3._elementRef.nativeElement.classList.remove('star_color')
    this.star4._elementRef.nativeElement.classList.remove('star_color')
    this.star5._elementRef.nativeElement.classList.remove('star_color')
    switch (stars){
      case 5:
        this.star5._elementRef.nativeElement.classList.add('star_color')
      case 4:
        this.star4._elementRef.nativeElement.classList.add('star_color')
      case 3:
        this.star3._elementRef.nativeElement.classList.add('star_color')
      case 2:
        this.star2._elementRef.nativeElement.classList.add('star_color')
        break;
    }
    this.stars = stars
  }

  backToHome(){
    this.dialogRef.close();
  }

  public getImageSrc(base64: any): any {
    return base64 ? this.sanitizer.bypassSecurityTrustResourceUrl('data:image/*;base64,' + base64) : './assets/images/no-image-transparent.png';
  }
}
