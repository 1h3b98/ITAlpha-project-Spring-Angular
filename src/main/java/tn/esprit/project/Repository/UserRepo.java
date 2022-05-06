package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    List<User> findByFirstName(String FName);

    @Query("select u.FavEvents from User u where u.userId=:uId ")
    List<Event> findAllFavEventByUser(@Param("uId") Long userId);

    User findByUsername(String username);


    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
    boolean existsByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);
    @Query(value ="select * FROM user m where m.user_id != :iduser1 "  ,nativeQuery = true)
    public List<User>findallUserMessages(@Param("iduser1")Long id1);

}
