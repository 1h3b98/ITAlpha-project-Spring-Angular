package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
