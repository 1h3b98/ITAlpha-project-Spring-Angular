package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.project.Entities.Evaluation;
import tn.esprit.project.Entities.User;

import java.util.List;

@Repository
public interface EvaluationRepo extends JpaRepository<Evaluation,Long> {

    public Evaluation getEvaluationsByUser(User u);
    public Boolean existsEvaluationByUser(User u);
}
