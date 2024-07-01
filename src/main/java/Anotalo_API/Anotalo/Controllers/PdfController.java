package Anotalo_API.Anotalo.Controllers;

import Anotalo_API.Anotalo.Models.Note;
import Anotalo_API.Anotalo.Services.NotesService;
import Anotalo_API.Anotalo.Services.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private NotesService noteService;

    @GetMapping("/{noteId}/export/pdf")
    public void exportNoteAsPdf(@PathVariable Long noteId, HttpServletResponse response) throws IOException {
        Optional<Note> optionalNote = noteService.findById(noteId);

        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            byte[] pdfBytes = pdfService.generatePdfFromNote(note);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=note_" + note.getId() + ".pdf");

            response.getOutputStream().write(pdfBytes);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.getWriter().write("Nota no encontrada para el ID: " + noteId);
        }
    }
}
