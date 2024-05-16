package Anotalo_API.Anotalo.Controllers;

import Anotalo_API.Anotalo.Models.Note;
import Anotalo_API.Anotalo.Services.NotesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Note>> getAllUserNotes(@PathVariable Long userId) {
        List<Note> notes = notesService.allUserNotes(userId);
        if (notes.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } else {
            return ResponseEntity.ok(notes); // 200 OK
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Note>> searchNotesByText(@RequestParam Long userId, @RequestParam String searchText) {
        if (userId == null || searchText == null || searchText.isEmpty()) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        List<Note> notes = notesService.searchNotesByText(userId, searchText);
        return ResponseEntity.ok(notes); // 200 OK
    }

    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        if (note == null || note.getTitle() == null || note.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        Note createdNote = notesService.createNote(note);
        return ResponseEntity.ok(createdNote); // 200 OK (with created note)
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateNote(@RequestBody Note note) {
        if (note == null || note.getId() == null || note.getTitle() == null || note.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        notesService.updateNote(note);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        notesService.deleteNote(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
