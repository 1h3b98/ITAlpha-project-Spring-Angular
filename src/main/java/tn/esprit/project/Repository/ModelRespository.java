package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.project.Entities.Model;

public interface ModelRespository extends CrudRepository<Model,Long> {
}
