import { Note } from './note.model';

export interface Section {
    id: number;
    title: string;
    note: Note[]; 
}
