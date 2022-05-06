package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Departement;

@Repository
public interface DepartementRepo extends JpaRepository<Departement,Long> {
    Departement findDepartementByTitle(String title);
}
