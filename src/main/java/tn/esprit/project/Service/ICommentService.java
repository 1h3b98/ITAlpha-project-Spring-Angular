package tn.esprit.project.Service;

import tn.esprit.project.Entities.Comment;
import tn.esprit.project.Entities.Post;

import java.util.List;

public interface ICommentService {
    public List<Comment> getCommentsPost(Long idpost);
    public Comment addComment(Comment c,Long idu,Long idp);
    public Comment updateComment(Comment c);
    public void deleteComment(Long id);
}
