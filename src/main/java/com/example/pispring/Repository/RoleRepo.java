package com.example.pispring.Repository;

import com.example.pispring.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}
