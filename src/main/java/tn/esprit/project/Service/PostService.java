package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Post;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.PostRepository;
import tn.esprit.project.Repository.RatingRepository;
import tn.esprit.project.Repository.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService implements  IPostService{

    @Autowired
    PostRepository pr ;
    @Autowired
    UserRepository ur ;
    @Autowired
    RatingRepository rr;
    @Override
    public Post getPost(Long id) {
        return pr.findById(id).get();
    }

    @Override
    public List<Post> getPosts() {
        List<Post> postes = (List<Post>) pr.findAll();
        return postes;
    }

    @Override
    public Post addPost(Post p, Long idUser) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        p.setCreateAt(timestamp);
        User user = ur.findById(idUser).get();
        p.setUserP(user);
        return pr.save(p);
    }

    @Override
    public Post updatePost(Post p, Long id) {
       Post post = pr.findById(id).get();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setUpdateAt(timestamp);
        return pr.save(post);
    }

    @Override
    public void deletePost(Long id) {
        Post post = pr.findById(id).get();
        pr.delete(post);
    }

    @Override
    public List<Post>  getPostByUser(Long idUser) {
        List<Post> post = new ArrayList<>();
        List<Post> posts = (List<Post>) pr.findAll();
        for (Post p:posts) {
            if (p.getUserP().getUserId()==idUser)
                post.add(p);
        }
        return post;
    }
}