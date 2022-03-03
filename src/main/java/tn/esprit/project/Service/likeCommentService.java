package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.project.Entities.Comment;
import tn.esprit.project.Entities.LikeComment;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.CommentRepository;
import tn.esprit.project.Repository.LikeCommentRepository;
import tn.esprit.project.Repository.PostRepository;
import tn.esprit.project.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class likeCommentService implements IlikeCommentService {


    @Autowired
    CommentRepository cr ;
    @Autowired
    UserRepository ur ;
    @Autowired
    LikeCommentRepository lcr ;
    @Override
    public void likeComment(LikeComment like ,  Long idcomment, Long idUser) {
        User user = ur.findById(idUser).get();
        Comment comment = cr.findById(idcomment).get();
        like.setUserLike(user);
        like.setCommentLiked(comment);
        like.setStatut(true);

    }

    @Override
    public void dislikeComment(LikeComment like , Long idcomment, Long idUser) {
        User user = ur.findById(idUser).get();
        Comment comment = cr.findById(idcomment).get();
        like.setUserLike(user);
        like.setCommentLiked(comment);
        like.setStatut(false);
    }
}
