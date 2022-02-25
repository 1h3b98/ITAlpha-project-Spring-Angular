package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.entities.ClassBadge;
import tn.esprit.project.entities.Evaluation;
@Repository
public interface EvaluationRepo extends JpaRepository<Evaluation,Long> {
}
