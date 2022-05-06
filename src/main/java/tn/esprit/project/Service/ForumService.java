package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Favoritesarticle;
import tn.esprit.project.Entities.Forum;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.FavoritesArticleRepository;
import tn.esprit.project.Repository.ForumRepository;
import tn.esprit.project.Repository.RatinggRepository;
import tn.esprit.project.Repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
@Service
public class ForumService implements IForumService{


    @Autowired
    ForumRepository fr ;
    @Autowired
    UserRepo ur;
    @Autowired
    RatinggRepository rr;
    @Autowired
    FavoritesArticleRepository ftr;

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
            p.setNbComment((long) p.getOpinions().size());
            aticlesf.add(p);
            fr.save(p);
        }
        return aticlesf;
    }
    public void addTofavorites(Long idarticle, Long idUser){
        User user = ur.findById(idUser).get();
        Forum forum = fr.findById(idarticle).get();
        Favoritesarticle favoritesarticle = new Favoritesarticle();
        favoritesarticle.setForum(forum);
        favoritesarticle.setUser(user);
        ftr.save(favoritesarticle);
    }

    public List<Forum> getFavorites(Long iduser){
        List<Forum> forums = new ArrayList<>();
        List<Favoritesarticle> forumList= (List<Favoritesarticle>) ftr.findAll();
        for (Favoritesarticle fa: forumList) {
            if(fa.getUser().getUserId()==iduser){
                forums.add(fa.getForum());
            }
        }
        return forums;
    }
}
