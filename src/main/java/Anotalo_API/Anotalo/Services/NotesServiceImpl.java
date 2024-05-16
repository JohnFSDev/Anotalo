package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Note;
import Anotalo_API.Anotalo.Repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesRepository noteRepository;

    @Override
    public List<Note> allUserNotes(Long id) {
        return noteRepository.findByUserId(id);
    }

    @Override
    public List<Note> searchNotesByText(Long idUser, String searchText) {
        return noteRepository.findByUserIdAndContentContaining(idUser, searchText);
    }

    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void updateNote(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
