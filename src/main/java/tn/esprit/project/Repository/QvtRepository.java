package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Qquestion;
import tn.esprit.project.Entities.Qvt;

@Repository
public interface QvtRepository extends CrudRepository<Qvt, Long> {
}
