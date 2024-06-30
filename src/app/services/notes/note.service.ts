// notes.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Note } from '../../models/note.model'; // Asegúrate de tener el modelo de Note definido
import { environment } from '../../environment';
@Injectable({
  providedIn: 'root'
})
export class NotesService {
  private apiUrl = `${environment.apiUrl}/notes`; // Reemplaza con la URL de tu API

  constructor(private http: HttpClient) {}

  getAllUserNotes(userId: number): Observable<Note[]> {
    return this.http.get<Note[]>(`${this.apiUrl}/user/${userId}`);
  }

  searchUserNotes(userId: number, searchText: string): Observable<Note[]> {
    return this.http.get<Note[]>(`${this.apiUrl}/search?userId=${userId}&searchText=${searchText}`);
  }

  createNote(note: Note): Observable<Note> {
    return this.http.post<Note>(`${this.apiUrl}/create`, note);
  }

  updateNote(note: Note): Observable<Note> {
    return this.http.put<Note>(`${this.apiUrl}/update`, note);
  }

  deleteNote(noteId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${noteId}`);
  }
}