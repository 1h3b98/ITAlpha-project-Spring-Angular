package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.project.entities.Post;

public interface PostRepository extends CrudRepository<Post , Long> {
}