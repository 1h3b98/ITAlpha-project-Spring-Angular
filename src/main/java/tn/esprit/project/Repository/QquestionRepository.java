package tn.esprit.project.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.project.Entities.*;
@Repository
public interface QquestionRepository extends JpaRepository<Qquestion,Long> {

}
