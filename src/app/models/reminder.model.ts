import { Note } from './note.model';

export interface Reminder {
    id: number;
    is_active: boolean;
    reminder_time: Date;
    note: Note;
}
