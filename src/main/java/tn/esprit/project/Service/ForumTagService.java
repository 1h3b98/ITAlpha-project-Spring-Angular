package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.ForumTag;
import tn.esprit.project.Repository.ForumTagRepository;

@Service
public class ForumTagService implements IforumTagService{
    @Autowired
    ForumTagRepository ttr;
    @Override
    public void addTag(ForumTag forumTag) {
        ttr.save(forumTag);
    }
}
