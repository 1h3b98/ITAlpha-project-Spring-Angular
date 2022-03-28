package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Favoritesarticle;

@Repository
public interface FavoritesArticleRepository extends CrudRepository<Favoritesarticle,Long> {

}
