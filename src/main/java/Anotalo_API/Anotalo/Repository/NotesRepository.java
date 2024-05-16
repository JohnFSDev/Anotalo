package Anotalo_API.Anotalo.Repository;
import Anotalo_API.Anotalo.Models.Note;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Note, Long> {

    public List<Note> findByUserId(Long id);

    public List<Note> findByUserIdAndContentContaining(Long idUser, String searchText);
    
}
