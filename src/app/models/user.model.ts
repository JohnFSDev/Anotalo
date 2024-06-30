import { Note } from "./note.model";

export interface User {
    id?: number;
    name?: string;
    email?: string;
    passwordHash?: string;
    salt?: string;
    registrationDate?: Date;
    notes?: Note[];  
}