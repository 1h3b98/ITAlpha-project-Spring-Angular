package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.ForumTag;

@Repository
public interface ForumTagRepository extends CrudRepository<ForumTag,Long> {

}
