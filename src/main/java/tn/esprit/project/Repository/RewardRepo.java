package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.entities.Evaluation;
import tn.esprit.project.entities.Reward;
@Repository
public interface RewardRepo extends JpaRepository<Reward,Long> {
}
