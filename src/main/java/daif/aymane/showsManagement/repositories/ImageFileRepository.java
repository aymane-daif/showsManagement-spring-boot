package daif.aymane.showsManagement.repositories;

import daif.aymane.showsManagement.models.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository<ImageFile, String> {
}
