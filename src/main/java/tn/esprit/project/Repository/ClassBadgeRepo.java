package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.entities.ClassBadge;
@Repository
public interface ClassBadgeRepo extends JpaRepository<ClassBadge,Long> {
}
