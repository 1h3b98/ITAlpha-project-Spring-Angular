package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Forum;
import tn.esprit.project.Entities.Post;
import tn.esprit.project.Entities.Rating;
import tn.esprit.project.Entities.RatingId;


@Repository
public interface RatingRepository extends CrudRepository<Rating, RatingId> {
    @Query("SELECT sum (j.value) from  Rating j where j.ratingId.form=:post")
    public float sommeDenoteByPost(Forum post);
    @Query("select count(j) from  Rating j where j.ratingId.form=:post")
    public int nbrPosts(Forum post);
}
