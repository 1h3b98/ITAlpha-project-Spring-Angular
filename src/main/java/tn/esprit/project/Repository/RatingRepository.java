package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.project.Entities.PubliciteOffre;
import tn.esprit.project.Entities.Rating;

public interface RatingRepository extends JpaRepository<Rating,Long> {
//    @Query("select sum (r.value) from  Rating r where r.publiciteOffre=:pub")
//    public int Sommedenotebyoffre(PubliciteOffre publiciteOffre);
//    @Query("select count(r) from  Rating r where r.publiciteOffre=:post")
//    public int  nbredoffre(PubliciteOffre publiciteOffre);
}
