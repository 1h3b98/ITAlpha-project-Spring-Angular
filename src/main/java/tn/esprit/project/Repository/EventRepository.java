package tn.esprit.project.Repository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.project.Entities.Event;

public interface EventRepository extends CrudRepository<Event,Long>  {

}