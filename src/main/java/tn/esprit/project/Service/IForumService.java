package tn.esprit.project.Service;

import tn.esprit.project.Entities.Forum;

import java.util.List;

public interface IForumService {
    public Forum addArticle(Forum f ,Long idUser);
    public void deleteForum(Long id);
    public Forum update(Long idforum);
    public List<Forum> getAll();
}
