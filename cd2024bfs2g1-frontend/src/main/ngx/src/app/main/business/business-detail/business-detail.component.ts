import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { OFormComponent, OTableComponent, OntimizeService } from 'ontimize-web-ngx';
import { GalleryImage, GalleryOptions } from 'ontimize-web-ngx-gallery';


@Component({
  selector: 'app-business-detail',
  templateUrl: './business-detail.component.html',
  styleUrls: ['./business-detail.component.css']
})
export class BusinessDetailComponent {

  @ViewChild('accountCustomerTable') accountTable: OTableComponent;
  @ViewChild('form') form: OFormComponent;

  constructor(
    protected sanitizer: DomSanitizer
  ) {
  }

  public galleryOptions: GalleryOptions[];
  public galleryImages: GalleryImage[];


	ngOnInit(): void {


    this.galleryOptions = [
      {
          width: "100%",
          thumbnailsColumns: 3,
          thumbnailsRows: 1,
          layout:"thumbnails-bottom",
          imageAutoPlay: true,
          imageAutoPlayInterval: 5000,
        }
    ];

    this.galleryImages = [
      {
        small: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/dd16a14b-4792-4c5e-a8a7-60902f118086.jpeg?im_w=1200',
        medium: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/dd16a14b-4792-4c5e-a8a7-60902f118086.jpeg?im_w=1200',
        big: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/dd16a14b-4792-4c5e-a8a7-60902f118086.jpeg?im_w=1200',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ac nisi nunc. Donec hendrerit lorem nisi, tincidunt maximus purus facilisis ac.'
      },
      {
        small: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/300ebc22-34bf-4270-b0b6-7eb06c71c177.jpeg?im_w=1200',
        medium: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/300ebc22-34bf-4270-b0b6-7eb06c71c177.jpeg?im_w=1200',
        big: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/300ebc22-34bf-4270-b0b6-7eb06c71c177.jpeg?im_w=1200',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut ullamcorper rhoncus urna id pretium. Donec rhoncus nisl nulla, eu fermentum ligula rutrum ut.'
      },
      {
        small: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/243a94fc-5d77-44ba-a920-275897a415c6.jpeg?im_w=1200',
        medium: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/243a94fc-5d77-44ba-a920-275897a415c6.jpeg?im_w=1200',
        big: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/243a94fc-5d77-44ba-a920-275897a415c6.jpeg?im_w=1200',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.Ut ac massa semper, facilisis turpis porta, dapibus elit.Integer vestibulum ipsum quis ultricies efficitur.'
      },
      {
        small: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/8019d49c-92da-4083-9976-999c1ec9225c.jpeg?im_w=1200',
        medium: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/8019d49c-92da-4083-9976-999c1ec9225c.jpeg?im_w=1200',
        big: 'https://a0.muscache.com/im/pictures/miso/Hosting-749084685607841888/original/8019d49c-92da-4083-9976-999c1ec9225c.jpeg?im_w=1200',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur vestibulum ex nulla, quis imperdiet ex interdum vel. Duis sit amet placerat purus, quis sodales ante.'
      }
    ];
  }
}
