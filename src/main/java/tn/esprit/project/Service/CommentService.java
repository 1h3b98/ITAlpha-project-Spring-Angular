package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Comment;
import tn.esprit.project.Entities.Post;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.CommentRepository;
import tn.esprit.project.Repository.PostRepository;
import tn.esprit.project.Repository.UserRepository;

import java.util.List;


@Service
public class CommentService implements ICommentService{

    @Autowired
    CommentRepository cr ;
    @Autowired
    UserRepository ur;
    @Autowired
    PostRepository pr ;

    @Override
    public List<Comment> getCommentsPost(Long idpost) {
        Post post = pr.findById(idpost).get();
        return post.getComments();
    }

    @Override
    public Comment addComment(Comment c, Long idu, Long idp) {
        User user = ur.findById(idu).get();
        Post post = pr.findById(idp).get();
        c.setUserComment(user);
        c.setPost(post);
        return cr.save(c);
    }

    @Override
    public Comment updateComment(Comment c) {
        return cr.save(c);
    }

    @Override
    public void deleteComment(Long id) {
    Comment comment = cr.findById(id).get();
    cr.delete(comment);
    }
}
