package Anotalo_API.Anotalo.Controllers;

import Anotalo_API.Anotalo.Models.Image;
import Anotalo_API.Anotalo.Services.ImagesService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImagesController {

    @Autowired
    private ImagesService imageService;


    @GetMapping("/note/{noteId}")
    public ResponseEntity<List<Image>> getImagesByNoteId(@PathVariable Long noteId) {
        List<Image> images = imageService.getImagesByNoteId(noteId);
        if (!images.isEmpty()) {
            return new ResponseEntity<>(images, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("noteId") Long noteId) throws IOException {
        try {
            Image savedImage = imageService.uploadImage(file, noteId);
            return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
