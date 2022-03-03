package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Forum;
import tn.esprit.project.Entities.Opinion;
import tn.esprit.project.Entities.Post;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.ForumRepository;
import tn.esprit.project.Repository.OpinionRepository;
import tn.esprit.project.Repository.UserRepository;

import java.util.List;

@Service
public class OpinionService implements IOpinionService {
    @Autowired
    ForumRepository fr;
    @Autowired
    OpinionRepository op ;
    @Autowired
    UserRepository ur ;

    @Override
    public List<Opinion> getOpinionByPost(Long idPost) {
       Forum post = fr.findById(idPost).get();
        return  post.getOpinions();
    }

    @Override
    public Opinion add(Opinion opinion, Long idUser, Long idForum) {
        User user = ur.findById(idUser).get();
        Forum forum = fr.findById(idForum).get();
        opinion.setUserOpinion(user);
        opinion.setForumOpinion(forum);
        return  op.save(opinion);
    }

    @Override
    public void deleteOpinion(Long idOpinion) {
    Opinion opinion = op.findById(idOpinion).get();
    op.delete(opinion);
    }

    @Override
    public Opinion update(Long idOpinion) {
        return null;
    }
}
