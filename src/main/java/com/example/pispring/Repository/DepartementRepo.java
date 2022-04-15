package com.example.pispring.Repository;

import com.example.pispring.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DepartementRepo extends JpaRepository<Departement,Long> {
    Departement findDepartementByTitle(String title);
}
