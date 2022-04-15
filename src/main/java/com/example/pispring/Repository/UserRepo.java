package com.example.pispring.Repository;

import com.example.pispring.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
   // List<User> findByFName(String FName);
    User findByUsername(String username);

    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
 boolean existsByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);
}

