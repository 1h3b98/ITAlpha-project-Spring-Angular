package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.project.Entities.ClassBadge;
import tn.esprit.project.Entities.Evaluation;
@Repository
public interface EvaluationRepo extends JpaRepository<Evaluation,Long> {
}
