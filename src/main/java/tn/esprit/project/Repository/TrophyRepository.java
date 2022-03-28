package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Evaluation;
import tn.esprit.project.Entities.Trophy;
@Repository
public interface TrophyRepository extends JpaRepository<Trophy,Long> {
    public Boolean existsTrophiesByEvaluation(Evaluation evaluation);
}
