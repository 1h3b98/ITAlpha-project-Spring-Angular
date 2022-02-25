package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.project.Entities.Qquestion;

public interface QvtRepository extends CrudRepository<Qquestion, Long> {
}
