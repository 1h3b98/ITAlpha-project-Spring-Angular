package tn.esprit.project.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Role;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}
