package Anotalo_API.Anotalo.Repository;

import Anotalo_API.Anotalo.Models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemindersRepository extends JpaRepository <Reminder,Long> {
    
}
