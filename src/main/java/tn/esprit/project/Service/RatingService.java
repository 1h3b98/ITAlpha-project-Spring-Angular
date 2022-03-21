package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.ForumRepository;
import tn.esprit.project.Repository.PostRepository;
import tn.esprit.project.Repository.RatingRepository;
import tn.esprit.project.Repository.UserRepository;

@Service
public class RatingService implements IRatingService{

    @Autowired
    UserRepository ur;
    @Autowired
    PostRepository pr;
    @Autowired
    ForumRepository fr;
    @Autowired
    RatingRepository rr;

    @Override
    public void ratePost(int note, Long idPost, Long idUser) {
        User user = ur.findById(idUser).get();
        Forum forum = fr.findById(idPost).get();
        Rating rating = new Rating();
        RatingId ratingId = new RatingId();
        ratingId.setUser(user);
        ratingId.setForm(forum);
        rating.setRatingId(ratingId);
        rating.setValue(note);
        rr.save(rating);

    }


    @Override
    public void removeRating(Long idpost, Long idUser) {
        RatingId ratingId = new RatingId();
        ratingId.setForm(fr.findById(idpost).orElse(null));
        ratingId.setUser(ur.findById(idUser).orElse(null));
        rr.deleteById(ratingId);
    }
}
