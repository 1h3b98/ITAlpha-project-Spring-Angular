package tn.esprit.project.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.project.Entities.*;
@Repository

public interface ScoreRepository extends JpaRepository<Score, Long>  {

}
