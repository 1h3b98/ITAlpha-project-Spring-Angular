package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.project.Entities.Forum;

public interface ForumRepository extends CrudRepository<Forum, Long> {
}
