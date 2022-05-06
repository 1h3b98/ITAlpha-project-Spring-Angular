package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Forum;
import tn.esprit.project.Entities.Ratingg;
import tn.esprit.project.Entities.RatingId;


@Repository
public interface RatinggRepository extends CrudRepository<Ratingg, RatingId> {
    @Query("SELECT sum (j.value) from  Ratingg j where j.ratingId.form=:post")
    public float sommeDenoteByPost(Forum post);
    @Query("select count(j) from  Ratingg j where j.ratingId.form=:post")
    public int nbrPosts(Forum post);
}
