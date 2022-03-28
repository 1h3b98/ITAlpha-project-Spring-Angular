package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.project.Entities.LikePost;
import tn.esprit.project.Entities.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Long> {


}
