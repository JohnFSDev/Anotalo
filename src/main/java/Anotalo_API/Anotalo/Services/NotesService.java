package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Note;
import java.util.List;


public interface NotesService {
    List<Note> allUserNotes(Long id);
    List<Note> searchNotesByText(Long idUser, String searchText);
    Note createNote(Note note);
    void updateNote(Note note);
    void deleteNote(Long id);
}
