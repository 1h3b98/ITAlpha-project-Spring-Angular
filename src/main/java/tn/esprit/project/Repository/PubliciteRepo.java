package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.PubliciteOffre;


import java.util.List;

@Repository
public interface PubliciteRepo extends JpaRepository<PubliciteOffre, Long> {
    List<PubliciteOffre> findPubliciteByTitre(String titre);


}
