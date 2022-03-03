package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.project.Entities.Forum;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.ForumRepository;
import tn.esprit.project.Repository.UserRepository;

import java.util.List;

public class ForumService implements IForumService{


    @Autowired
    ForumRepository fr ;
    @Autowired
    UserRepository ur;
    @Override
    public Forum addArticle(Forum f,Long idUser) {
        User user = ur.findById(idUser).get();
        f.setUserForum(user);
        return fr.save(f) ;
    }

    @Override
    public void deleteForum(Long id) {
    Forum forum = fr.findById(id).get();
    fr.delete(forum);
    }

    @Override
    public Forum update(Long idforum) {
        return null;
    }

    @Override
    public List<Forum> getAll() {
        return (List<Forum>) fr.findAll();
    }
}
