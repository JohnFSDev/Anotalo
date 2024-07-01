package Anotalo_API.Anotalo.Repository;

import Anotalo_API.Anotalo.Models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionsRepository extends JpaRepository <Section,Long> {
    
}
