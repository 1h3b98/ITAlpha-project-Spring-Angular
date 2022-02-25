package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.project.Entities.Evaluation;
import tn.esprit.project.Entities.Vote;
@Repository
public interface VoteRepo extends JpaRepository<Vote,Long> {
}
