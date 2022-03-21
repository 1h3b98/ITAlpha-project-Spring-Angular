package tn.esprit.project.Service;

import tn.esprit.project.Entities.Signaler;

public interface ISignalerService {
    public void SignalerForum(Signaler signaler,Long idForum, Long iduser);
}
