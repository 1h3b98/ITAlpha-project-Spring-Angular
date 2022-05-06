package tn.esprit.project.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class CommentService implements ICommentService{

    @Autowired
    CommentRepository cr ;
    @Autowired
    UserRepo ur;
    @Autowired
    PostRepository pr ;
    @Autowired
    NotificationRepository nr;
    @Autowired
    LikeCommentRepository lcr;


    @Override
    public List<Comment> getCommentsPost(Long idpost) {
        Post post = pr.findById(idpost).get();
        return post.getComments();
    }

    @Override
    public Comment addComment(Comment c, Long idu, Long idp) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User user = ur.findById(idu).get();
        Post post = pr.findById(idp).get();
        post.setNbrComment(post.getNbrComment()+1);
        c.setUserComment(user);
        c.setPost(post);
        c.setCreateAt(timestamp);
        cr.save(c);
        notifyall( post , c);
        return c;
    }

    @Override
    public Comment updateComment(Comment c, Long idCommment) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Comment comment = cr.findById(idCommment).get();
        comment.setUpdateAt(timestamp);
        comment.setContent(c.getContent());
        return cr.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
    Comment comment = cr.findById(id).get();
    cr.delete(comment);
    }

    public void notifyall(Post post , Comment comment){
        List<Comment> comments = post.getComments();
        for (Comment c: comments) {
            Notification nt = new Notification();
            nt.setUser(c.getUserComment());
            nt.setContent(comment.getUserComment().getFirstName()+" add new comment");
            nr.save(nt);
           log.info("");
        }
    }
    /***************************** LIKE *******************************/
    @Override
    public LikeComment makelikecomment(Long idcomment, Long idUser) {
        LikeComment likeComment = new LikeComment();
        LikeCommentId likeCommentId = new LikeCommentId();
        likeCommentId.setUser1(ur.findById(idUser).orElse(null));
        likeCommentId.setComment1(cr.findById(idcomment).orElse(null));
        likeComment.setLikeCommentId(likeCommentId);
        likeComment.setValue(false);
        return lcr.save(likeComment);
    }

    @Override
    public LikeComment makedislikecomment(Long idcomment, Long idUser) {
        LikeComment likeComment = new LikeComment();
        LikeCommentId likeCommentId = new LikeCommentId();
        likeCommentId.setUser1(ur.findById(idUser).orElse(null));
        likeCommentId.setComment1(cr.findById(idcomment).orElse(null));
        likeComment.setLikeCommentId(likeCommentId);
        likeComment.setValue(true);
        return lcr.save(likeComment);
    }

    @Override
    public void removeLike(Long idcomment, Long idUser) {
        LikeCommentId likeCommentId = new LikeCommentId();
        likeCommentId.setUser1(ur.findById(idUser).orElse(null));
        likeCommentId.setComment1(cr.findById(idcomment).orElse(null));
        lcr.deleteById(likeCommentId);
    }
}
