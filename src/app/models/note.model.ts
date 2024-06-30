import { User } from './user.model';

export interface Note {
    id?: number;
    title: string;
    content: string;
    createdAt?: Date;
    updatedAt?: Date;
    user: User; 
}