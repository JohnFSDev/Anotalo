package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Note;
import Anotalo_API.Anotalo.Repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesRepository noteRepository;

    @Override
    public List<Note> allUserNotes(Long id) {
        return noteRepository.findByUserId(id);
    }

    @Override
    public List<Note> SearchUserNotesByText(Long idUser, String text) {
        return noteRepository.SearchUserNotesByText(idUser, text);
    }

    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note updateNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }
}
