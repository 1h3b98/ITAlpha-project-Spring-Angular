package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.project.Entities.Tag;

public interface TagRepository extends CrudRepository<Tag,Long> {
}
