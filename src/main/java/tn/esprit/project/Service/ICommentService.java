package tn.esprit.project.Service;

import tn.esprit.project.Entities.Comment;
import tn.esprit.project.Entities.LikeComment;
import tn.esprit.project.Entities.LikePost;
import tn.esprit.project.Entities.Post;

import java.util.List;

public interface ICommentService {
    public List<Comment> getCommentsPost(Long idpost);
    public Comment addComment(Comment c,Long idu,Long idp);
    public Comment updateComment(Comment c, Long idCommment);
    public void deleteComment(Long id);
    public LikeComment makelikecomment(Long idcomment, Long idUser);
    public LikeComment  makedislikecomment(Long idcomment, Long idUser);
    public void removeLike(Long idcomment, Long idUser);
}
