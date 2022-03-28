package tn.esprit.project.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.project.Entities.*;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	@Query(value ="select * from notification n where n.user_user_id = :iduser",nativeQuery=true)
	public List<Notification> shownotif(@Param("iduser") Long iduser);
}
