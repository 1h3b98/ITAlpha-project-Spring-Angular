package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.project.entities.Comment;


public interface CommentRepository extends CrudRepository<Comment, Long> {
}
