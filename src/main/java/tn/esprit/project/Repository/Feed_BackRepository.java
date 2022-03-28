package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.FeedBack;
import tn.esprit.project.Entities.User;

@Repository
public interface Feed_BackRepository extends JpaRepository<FeedBack,Long> {
    Boolean existsByReciever(User u);
}
