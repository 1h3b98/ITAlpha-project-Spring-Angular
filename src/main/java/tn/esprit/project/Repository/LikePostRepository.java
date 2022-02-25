package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.project.entities.Comment;
import tn.esprit.project.entities.LikePost;

public interface LikePostRepository extends CrudRepository<LikePost, Long> {
}
