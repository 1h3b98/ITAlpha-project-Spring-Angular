package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Comment;
import tn.esprit.project.Entities.Forum;
import tn.esprit.project.Entities.LikeComment;
import tn.esprit.project.Entities.LikeCommentId;

@Repository
public interface LikeCommentRepository extends CrudRepository<LikeComment, LikeCommentId> {
}
