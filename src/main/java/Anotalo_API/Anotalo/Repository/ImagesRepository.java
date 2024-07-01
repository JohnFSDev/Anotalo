package Anotalo_API.Anotalo.Repository;

import Anotalo_API.Anotalo.Models.Image;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository <Image,Long> {

    public List<Image> findByNoteId(Long noteId);
    
}
