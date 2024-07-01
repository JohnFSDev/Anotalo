package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Section;
import Anotalo_API.Anotalo.Repository.SectionsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionsServiceImpl implements SectionsService {

    @Autowired
    private SectionsRepository sectionsRepository;

    @Override
    public List<Section> getAllSections() {
        return sectionsRepository.findAll();
    }

    @Override
    public Section getSectionById(Long id) {
        Optional<Section> sectionOptional = sectionsRepository.findById(id);
        return sectionOptional.orElse(null);
    }

    @Override
    public Section createSection(Section section) {
        return sectionsRepository.save(section);
    }

    @Override
    public Section updateSection(Long id, Section section) {
        Optional<Section> existingSectionOptional = sectionsRepository.findById(id);
        if (existingSectionOptional.isPresent()) {
            Section existingSection = existingSectionOptional.get();
            existingSection.setTitle(section.getTitle());
            // Aquí puedes actualizar otras propiedades según sea necesario
            return sectionsRepository.save(existingSection);
        } else {
            return null; // O lanza una excepción si lo prefieres
        }
    }

    @Override
    public void deleteSection(Long id) {
        sectionsRepository.deleteById(id);
    }
}
