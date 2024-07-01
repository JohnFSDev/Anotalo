package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Section;
import java.util.List;

public interface SectionsService {

    List<Section> getAllSections();
    Section getSectionById(Long id);
    Section createSection(Section section);
    Section updateSection(Long id, Section section);
    void deleteSection(Long id);
  
}
