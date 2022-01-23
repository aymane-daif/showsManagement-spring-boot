package daif.aymane.showsManagement.repositories;

import daif.aymane.showsManagement.models.AppUser;
import daif.aymane.showsManagement.models.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvShowRepository extends JpaRepository<TVShow, Long> {
   List<TVShow> findAllByUser(AppUser user);
   TVShow findByShowId(Long showId);
}
