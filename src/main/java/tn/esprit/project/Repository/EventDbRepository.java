package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.EventDb;

@Repository
public interface EventDbRepository extends JpaRepository<EventDb, String> {
}
