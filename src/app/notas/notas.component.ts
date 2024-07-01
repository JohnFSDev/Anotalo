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

  Notes: Note[] = [];
  Sections: string[] = ['Sección 1', 'Sección 2'];
  selectedNote: any = { id: null, title: '', content: ''};

  constructor(private notesService: NotesService) {}

  ngOnInit(): void {
    this.getAllNotesForUser(); // Llama a la función para obtener todas las notas al inicializar el componente
  }

  getAllNotesForUser(): void {
    const userId = 1; // Suponiendo que tienes el userId almacenado o lo obtienes de alguna manera
    this.notesService.getAllUserNotes(userId).subscribe(
      (notes: Note[]) => {
        this.Notes = notes; // Asigna las notas obtenidas al arreglo local
      },
      (error) => {
        console.error('Error al obtener las notas:', error);
        // Aquí podrías manejar el error de manera adecuada, por ejemplo, mostrando un mensaje al usuario
      }
    );
  }

  // Método para seleccionar una nota
  seleccionarNota(nota: Note): void {
    this.selectedNote.id = nota.id;
    this.selectedNote.title = nota.title;
    this.selectedNote.content = nota.content;
  }

// Método para abrir modal de eliminación de nota
openModalEliminar(id: number): void {
  Swal.fire({
    title: '¿Estás seguro?',
    text: 'No podrás revertir esto',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Sí, eliminar',
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.isConfirmed) {
      if (id !== undefined) {
        console.log('ID de la nota seleccionada:', id);
        this.deleteNote(id); // Llama al método para eliminar la nota solo si nota.id está definido
      } else {
        console.error('El ID de la nota es indefinido.');
      }
    }
  });
}

  // Método para eliminar una nota
  deleteNote(noteId: number): void {
    this.notesService.deleteNote(noteId).subscribe(
      () => {
        // Filtra la nota eliminada del arreglo local
        this.Notes = this.Notes.filter(n => n.id !== noteId);
        Swal.fire('Nota eliminada', 'La nota se ha eliminado correctamente.', 'success');
      },
      (error) => {
        console.error('Error al eliminar la nota:', error);
        // Aquí podrías manejar el error de manera adecuada, por ejemplo, mostrando un mensaje al usuario
      }
    );
  }

  // Método para actualizar una nota existente
  updateNote(): void {
    const updatedNote: Note = {
      title: this.selectedNote.title,
      content: this.selectedNote.content
    };

    // Suponiendo que tienes la lógica para identificar la nota que se está actualizando
    const noteToUpdate: Note | undefined  = this.Notes.find(n => n.title === this.selectedNote.title);

    if (noteToUpdate) {
      updatedNote.id = noteToUpdate.id; // Asegúrate de tener el ID de la nota que se está actualizando
      this.notesService.updateNote(updatedNote).subscribe(
        (updatedNoteResponse: Note) => {
          // Actualiza la nota en el arreglo local
          const index = this.Notes.findIndex(n => n.id === updatedNoteResponse.id);
          if (index !== -1) {
            this.Notes[index] = updatedNoteResponse;
          }
          Swal.fire('Nota actualizada', 'La nota se ha actualizado correctamente.', 'success');
        },
        (error) => {
          console.error('Error al actualizar la nota:', error);
          // Aquí podrías manejar el error de manera adecuada, por ejemplo, mostrando un mensaje al usuario
        }
      );
    }
  }

  // Método para abrir modal de agregar a sección
  openModalAgregar(): void {
    Swal.fire({
      title: 'Agregar a sección',
      input: 'select',
      inputOptions: this.Sections.reduce((options, seccion) => {
        return { ...options, [seccion]: seccion };
      }, {}),
      inputPlaceholder: 'Selecciona una sección',
      showCancelButton: true
    }).then((result) => {
      if (result.isConfirmed) {
        // Implementación simulada de agregar la nota a la sección seleccionada
        Swal.fire('Agregado!', `Nota agregada a ${result.value}.`, 'success');
      }
    });
  }

}