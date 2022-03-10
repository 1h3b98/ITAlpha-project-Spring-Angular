package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    List<User> findByFName(String FName);
    
    @Query("select u.FavEvents from User u where u.userId=:uId ")
    List<Event> findAllFavEventByUser(@Param("uId")Long userId);
}
