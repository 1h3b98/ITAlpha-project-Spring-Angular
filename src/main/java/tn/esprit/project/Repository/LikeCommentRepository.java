package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.project.Entities.Comment;
import tn.esprit.project.Entities.Forum;

public interface LikeCommentRepository extends CrudRepository<Forum, Long> {
}
