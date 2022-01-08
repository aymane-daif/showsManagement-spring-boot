package daif.aymane.showsManagement.repositories;

import daif.aymane.showsManagement.models.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
