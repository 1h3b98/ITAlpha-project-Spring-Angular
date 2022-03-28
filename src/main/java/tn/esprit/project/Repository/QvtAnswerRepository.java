package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.QvtAnswer;

@Repository
public interface QvtAnswerRepository extends CrudRepository<QvtAnswer, Long> {
}
