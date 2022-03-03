package tn.esprit.project.Service;


import tn.esprit.project.Entities.Opinion;

import java.util.List;

public interface IOpinionService  {
    public List<Opinion> getOpinionByPost(Long idPost);
    public Opinion add(Opinion opinion, Long idUser , Long idForum);
    public void deleteOpinion (Long idOpinion);
    public Opinion update (Long idOpinion);
}
