package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Event;
@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
}
