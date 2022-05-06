package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.ForumRepository;
import tn.esprit.project.Repository.PostRepository;
import tn.esprit.project.Repository.RatinggRepository;
import tn.esprit.project.Repository.UserRepo;

@Service
public class RatingService implements IRatingService{

    @Autowired
    UserRepo ur;
    @Autowired
    PostRepository pr;
    @Autowired
    ForumRepository fr;
    @Autowired
    RatinggRepository rr;

    @Override
    public void ratearticle(float note, Long idarticle, Long idUser) {
        User user = ur.findById(idUser).get();
        Forum forum = fr.findById(idarticle).get();
        Ratingg ratingg = new Ratingg();
        RatingId ratingId = new RatingId();
        ratingId.setUser(user);
        ratingId.setForm(forum);
        ratingg.setRatingId(ratingId);
        ratingg.setValue(note);
        rr.save(ratingg);

    }


    @Override
    public void removeRating(Long idpost, Long idUser) {
        RatingId ratingId = new RatingId();
        ratingId.setForm(fr.findById(idpost).orElse(null));
        ratingId.setUser(ur.findById(idUser).orElse(null));
        rr.deleteById(ratingId);
    }
}
