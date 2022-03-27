package tn.esprit.project.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import tn.esprit.project.Entities.*;
@Repository
public interface QquestionRepository extends CrudRepository<Qquestion,Long> {
	//@Query(value = "SELECT q FROM Qquestion q WHERE q.Qid= ?1")
	//List<Qquestion> retrieveallQuestionsForquiz(Long id);
}
