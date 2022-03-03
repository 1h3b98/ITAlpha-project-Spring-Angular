package tn.esprit.project.Service;

import tn.esprit.project.Entities.Comment;
import tn.esprit.project.Entities.LikeComment;

public interface IlikeCommentService {
    public void likeComment(LikeComment like ,Long idcomment , Long idUser);
    public void dislikeComment(LikeComment like ,Long idcomment , Long idUser);
}
