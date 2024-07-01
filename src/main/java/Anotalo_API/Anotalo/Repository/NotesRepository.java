package Anotalo_API.Anotalo.Repository;
import Anotalo_API.Anotalo.Models.Note;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {

    public List<Note> findByUserId(Long id);
    @Query("SELECT n FROM Note n WHERE n.user.id = :userId AND (LOWER(n.title) LIKE LOWER(concat('%', :query, '%')) OR LOWER(n.content) LIKE LOWER(concat('%', :query, '%')))")
    public List<Note> SearchUserNotesByText(@Param("userId") Long userId, @Param("query") String text);
    
}
