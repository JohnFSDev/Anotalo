package Anotalo_API.Anotalo.Repository;

import Anotalo_API.Anotalo.Models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemindersRepository extends JpaRepository <Reminder,Long> {
    
}
