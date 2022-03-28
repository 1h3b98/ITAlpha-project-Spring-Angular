package tn.esprit.project.Service;

import tn.esprit.project.Entities.Post;
import tn.esprit.project.Entities.User;

public interface IRatingService {
    public void ratearticle(float note , Long idPost, Long idUser);
    public void removeRating(Long idpost , Long idUser);
}
