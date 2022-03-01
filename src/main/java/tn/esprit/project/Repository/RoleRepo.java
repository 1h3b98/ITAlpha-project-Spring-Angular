package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
}
