package com.example.pispring.Repository;

import com.example.pispring.Entities.PubliciteOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PubliciteRepo extends JpaRepository<PubliciteOffre, Long> {
    List<PubliciteOffre> findPubliciteByTitre(String titre);


}
