package daif.aymane.showsManagement.repositories;

import daif.aymane.showsManagement.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
    public void deleteByUsername(String username);
    public boolean existsByUsername(String username);
}
