package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.project.entities.Comment;
import tn.esprit.project.entities.Forum;

public interface LikeCommentRepository extends CrudRepository<Forum, Long> {
}
