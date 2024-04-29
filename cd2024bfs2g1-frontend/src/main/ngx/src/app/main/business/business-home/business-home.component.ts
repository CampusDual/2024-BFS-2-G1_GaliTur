import { Component } from '@angular/core';
import { GalleryImage, GalleryOptions } from 'ontimize-web-ngx-gallery';
import { GalleryImageSize } from 'ontimize-web-ngx-gallery';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'app-business-home',
  templateUrl: './business-home.component.html',
  styleUrls: ['./business-home.component.css']
})
export class BusinessHomeComponent {

public galleryOptions: GalleryOptions[];
public galleryImages: GalleryImage[];
selectedOption: string;

  getDataArray() {
    const array: Array<Object> = [];
    array.push({
      'key': 1,
      'value': 'Restaurant'
    });
    array.push({
      'key': 2,
      'value': 'Lodging'
    });
    array.push({
      'key': 3,
      'value': 'Agency guide'
    });
    return array;
  }

  getValue() {
    return 1;
  }



  public languagesArray = [{
    countryCode: 1,
    countryText: 'Spanish'
  }, {
    countryCode: 2,
    countryText: 'Galician'
  }, {
    countryCode: 3,
    countryText: 'English'
  }, {
    countryCode: 4,
    countryText: 'German'
  }, {
    countryCode: 5,
    countryText: 'Portuguese'
  }, {
    countryCode: 6,
    countryText: 'French'
  }

  ];


  provincias = [
    { nombre: 'A Coruña', ciudades: [
      {ciudad: 'Coruña'}, 
      {ciudad: 'Ferrol'}, 
      {ciudad: 'Santiago de Compostela'}] },

    { nombre: 'Lugo', ciudades: [
      {ciudad: 'Lugo'}, 
      {ciudad: 'Monforte de Lemos'}, 
      {ciudad: 'Viveiro'}] },

    { nombre: 'Ourense', ciudades: [
      {ciudad: 'Ourense'}, 
      {ciudad: 'Verín'}, 
      {ciudad: 'Xinzo de Limia'}] },

    { nombre: 'Pontevedra', ciudades: [
      {ciudad: 'Pontevedra'}, 
      {ciudad: 'Vigo'}, 
      {ciudad: 'Marín'}] }
  ];


  provinciaSeleccionada: any;
  ciudadesFiltradas$: Observable<string[]> = of([]);

  constructor() {
    this.provinciaSeleccionada = this.provincias[0]; // Por defecto seleccionamos la primera provincia
    this.ciudadesFiltradas$ = of(this.provinciaSeleccionada.ciudades);
  }

  onProvinciaChange(event) {
    this.provinciaSeleccionada = this.provincias.find((p) => p.nombre === event)
    this.ciudadesFiltradas$ = of(this.provinciaSeleccionada.ciudades);
  }


  



  ngOnInit(): void {
    this.selectedOption = 'restaurant';
  
    this.galleryOptions = [
      {

          breakpoint: 1920,
          height: "400px",
          width: "400px",
          image: true,
          thumbnails: true,
          preview: true,
          imageArrows: true,
          imagePercent: 100,
          imageSize: GalleryImageSize.Cover,
          thumbnailSize: GalleryImageSize.Cover,
          thumbnailsColumns: 3,
          thumbnailsRows: 1,
          thumbnailsPercent: 25,
          thumbnailsMargin: 10,
          thumbnailMargin: 10,
          previewArrows: true,
          previewAutoPlay: false,
          previewCloseOnClick: true,
          previewCloseOnEsc: true,
          previewKeyboardNavigation: true,
          previewDownload: true,
          previewRotate: true,
          previewZoom: true,
          previewDescription: false,
          previewFullscreen: true
        }
        
    ];

    this.galleryImages = [
      {
        small: 'https://picsum.photos/id/237/150/100.jpg',
        medium: 'https://picsum.photos/id/237/600/450.jpg',
        big: 'https://picsum.photos/id/237/600/450.jpg',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ac nisi nunc. Donec hendrerit lorem nisi, tincidunt maximus purus facilisis ac.'
      },
      {
        small: 'https://picsum.photos/id/1062/150/100.jpg',
        medium: 'https://picsum.photos/id/1062/600/450.jpg',
        big: 'https://picsum.photos/id/1062/600/450.jpg',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut ullamcorper rhoncus urna id pretium. Donec rhoncus nisl nulla, eu fermentum ligula rutrum ut.'
      },
      {
        small: 'https://picsum.photos/id/1012/150/100.jpg',
        medium: 'https://picsum.photos/id/1012/600/450.jpg',
        big: 'https://picsum.photos/id/1012/600/450.jpg',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.Ut ac massa semper, facilisis turpis porta, dapibus elit.Integer vestibulum ipsum quis ultricies efficitur.'
      },
      {
        small: 'https://picsum.photos/id/1025/150/100.jpg',
        medium: 'https://picsum.photos/id/1025/600/450.jpg',
        big: 'https://picsum.photos/id/1025/600/450.jpg',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur vestibulum ex nulla, quis imperdiet ex interdum vel. Duis sit amet placerat purus, quis sodales ante.'
      }
    ];


  }

  
  
  

}
