package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.project.Entities.Comment;
import tn.esprit.project.Entities.LikePost;

public interface LikePostRepository extends CrudRepository<LikePost, Long> {
}
