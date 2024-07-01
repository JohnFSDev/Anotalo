package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Note;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;


public interface NotesService {
    List<Note> allUserNotes(Long id);
    List<Note> SearchUserNotesByText(@Param("userId") Long userId, @Param("query") String query);
    Note createNote(Note note);
    Optional<Note> findById(Long id);
    Note updateNote(Note note);
    void deleteNote(Long id);
}
