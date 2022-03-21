package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Forum;
import tn.esprit.project.Entities.Signaler;
import tn.esprit.project.Entities.SignalerId;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.ForumRepository;
import tn.esprit.project.Repository.SignalerRepository;
import tn.esprit.project.Repository.UserRepository;

@Service
public class SignalerService implements ISignalerService{
    @Autowired
    ForumRepository fr ;
    @Autowired
    UserRepository ur;
    @Autowired
    SignalerRepository sr;

    @Override
    public void SignalerForum(Signaler signaler , Long idForum , Long iduser) {
        User user = ur.findById(iduser).get();
        Forum forum = fr.findById(idForum).get();
        Signaler signalerf = new Signaler();
        SignalerId signalerId = new SignalerId();
        signalerId.setForm(forum);
        signalerId.setUser(user);
        signalerf.setDescription(signaler.getDescription());
        signalerf.setSingalerType(signaler.getSingalerType());
        signalerf.setSignalerId(signalerId);
        sr.save(signalerf);
    }
}
