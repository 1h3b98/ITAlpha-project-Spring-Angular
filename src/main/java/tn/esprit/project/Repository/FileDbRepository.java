package tn.esprit.project.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.FileDb;


@Repository
public interface FileDbRepository extends JpaRepository<FileDb, String>{

}