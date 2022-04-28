package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.User;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    @Query("select e.userL from Event e where e.eventId=:eId ")
    List<User> findUserListByEvent(@Param("eId") Long eventId);
}
