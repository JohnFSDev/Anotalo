package Anotalo_API.Anotalo.Repository;

import Anotalo_API.Anotalo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository <User,Long> {
    
}
