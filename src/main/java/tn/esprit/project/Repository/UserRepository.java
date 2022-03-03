package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.project.Entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
