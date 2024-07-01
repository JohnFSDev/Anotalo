package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Image;
import Anotalo_API.Anotalo.Models.Note;
import Anotalo_API.Anotalo.Repository.ImagesRepository;
import Anotalo_API.Anotalo.Repository.NotesRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    private ImagesRepository imageRepository;

    @Autowired
    private NotesRepository noteRepository;


    @Override
    public List<Image> getImagesByNoteId(Long noteId) {
        return imageRepository.findByNoteId(noteId);
    }

    @Override
    public Image uploadImage(MultipartFile file, Long noteId) throws IOException {
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            Image image = new Image();
            image.setImage(file.getBytes());
            image.setNote(note);
            return imageRepository.save(image);
        } else {
            throw new IllegalArgumentException("La nota no se ha encontrado");
        }
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}
