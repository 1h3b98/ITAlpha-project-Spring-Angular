package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.SimilarU;
@Repository
public interface SimilarRepository extends CrudRepository<SimilarU,Long> {
}
