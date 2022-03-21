package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Forum;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.ForumRepository;
import tn.esprit.project.Repository.RatingRepository;
import tn.esprit.project.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class ForumService implements IForumService{


    @Autowired
    ForumRepository fr ;
    @Autowired
    UserRepository ur;
    @Autowired
    RatingRepository rr;

    @Override
    public Forum addArticle(@RequestBody Forum f,@PathVariable("id") Long idUser) {
        User user = ur.findById(idUser).get();
        f.setUserForum(user);
        return fr.save(f) ;
    }

    @Override
    public void deleteForum(Long id) {
    Forum forum = fr.findById(id).get();
    fr.delete(forum);
    }

    @Override
    public Forum update(Long idforum) {
        return null;
    }

    @Override
    public List<Forum> getAll() {
        return (List<Forum>) fr.findAll();
    }

    @Override
    public List<Forum> getArticles() {
        List<Forum> aticles = (List<Forum>) fr.findAll();
        List<Forum> aticlesf= new ArrayList<>();
        for (Forum p:aticles) {
            p.setRating(rr.sommeDenoteByPost(p)/rr.nbrPosts(p));
            aticlesf.add(p);
            fr.save(p);
        }
        return aticlesf;
    }
}
