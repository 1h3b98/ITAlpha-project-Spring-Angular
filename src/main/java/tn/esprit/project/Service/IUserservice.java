package tn.esprit.project.Service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.User;

@Repository
public interface IUserservice extends CrudRepository<User,Long> {
}
