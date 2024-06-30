import { Note } from './note.model';

export interface Image {
    id: number;
    image: Blob; 
    note: Note;  
}