package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByFName(String FName);

    @Query("select u.FavEvents from User u where u.userId=:uId ")
    List<Event> findAllFavEventByUser(@Param("uId") Long userId);
}
