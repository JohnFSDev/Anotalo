import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NotesService } from '../services/notes/note.service';
import { Note } from '../models/note.model';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-notas',
  standalone: true,
  templateUrl: './notas.component.html',
  styleUrls: ['./notas.component.css'],
  imports: [CommonModule, FormsModule]
})
export class NotasComponent implements OnInit {

  listaNotas: any[] = [
    { id: 1, tituloNota: 'Nota 1', descripcionNota: 'Descripción de la nota 1', fechaModificacion: '2024-06-30' },
    { id: 2, tituloNota: 'Nota 2', descripcionNota: 'Descripción de la nota 2', fechaModificacion: '2024-06-29' },
    { id: 3, tituloNota: 'Nota 3', descripcionNota: 'Descripción de la nota 3', fechaModificacion: '2024-06-28' }
  ];

  listaSecciones: string[] = ['Sección 1', 'Sección 2']; // Ejemplo de lista de secciones
  tituloNotaSeleccionada: string = '';
  descripcionNotaSeleccionada: string = '';

  constructor() {}

  ngOnInit(): void {}

  seleccionarNota(titulo: string, descripcion: string): void {
    this.tituloNotaSeleccionada = titulo;
    this.descripcionNotaSeleccionada = descripcion;
  }

  openModalEliminar(): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'No podrás revertir esto',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        // Aquí se eliminaría la nota seleccionada (implementación simulada)
        Swal.fire('Eliminado!', 'Tu nota ha sido eliminada.', 'success');
      }
    });
  }

  openModalAgregar(): void {
    Swal.fire({
      title: 'Agregar a sección',
      input: 'select',
      inputOptions: this.listaSecciones.reduce((options, seccion) => {
        return { ...options, [seccion]: seccion };
      }, {}),
      inputPlaceholder: 'Selecciona una sección',
      showCancelButton: true
    }).then((result) => {
      if (result.isConfirmed) {
        // Aquí se agregaría la nota a la sección seleccionada (implementación simulada)
        Swal.fire('Agregado!', `Nota agregada a ${result.value}.`, 'success');
      }
    });
  }

  clearForm(): void {
    this.tituloNotaSeleccionada = '';
    this.descripcionNotaSeleccionada = '';
  }
}