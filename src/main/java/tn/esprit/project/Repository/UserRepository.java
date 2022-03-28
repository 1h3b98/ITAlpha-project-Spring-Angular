package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
