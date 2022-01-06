package daif.aymane.showsManagement.repositories;

import daif.aymane.showsManagement.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    public UserRole findByRoleName(String roleName);
}
