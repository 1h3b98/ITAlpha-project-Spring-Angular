package com.example.pispring.Repository;

import com.example.pispring.Entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {
//    @Query("select sum (r.value) from  Rating r where r.publiciteOffre=:pub")
//    public int Sommedenotebyoffre(PubliciteOffre publiciteOffre);
//    @Query("select count(r) from  Rating r where r.publiciteOffre=:post")
//    public int  nbredoffre(PubliciteOffre publiciteOffre);

    @Query("select r.publiciteOffre.titre from Rating  r where (r.value)=(select max(r.value) from Rating r)")
    public String bestoffre();

}
