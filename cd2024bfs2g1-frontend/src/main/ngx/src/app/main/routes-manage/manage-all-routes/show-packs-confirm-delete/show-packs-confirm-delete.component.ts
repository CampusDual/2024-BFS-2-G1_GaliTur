import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-show-packs-confirm-delete',
  templateUrl: './show-packs-confirm-delete.component.html',
  styleUrls: ['./show-packs-confirm-delete.component.css']
})
export class ShowPacksConfirmDeleteComponent {

  mensaje: string=''
  packs: [] = []

  constructor(@Inject(MAT_DIALOG_DATA) public data: any){
    this.mensaje = data.mensaje
    this.packs = data.packs
  }

}