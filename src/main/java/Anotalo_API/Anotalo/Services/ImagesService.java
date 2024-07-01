package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Image;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;


public interface ImagesService {

    List<Image> getImagesByNoteId(Long noteId);

    Image uploadImage(MultipartFile file, Long noteId) throws IOException;

    void deleteImage(Long id);
}