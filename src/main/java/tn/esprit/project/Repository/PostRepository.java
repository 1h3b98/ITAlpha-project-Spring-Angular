package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.project.Entities.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
}
