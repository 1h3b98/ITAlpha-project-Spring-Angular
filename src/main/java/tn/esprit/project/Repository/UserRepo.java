package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    List<User> findByFName(String FName);
}
