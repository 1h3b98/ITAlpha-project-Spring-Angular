package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Qvt;
import tn.esprit.project.Entities.QvtAnswer;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.QvtAnswerRepository;
import tn.esprit.project.Repository.QvtRepository;
import tn.esprit.project.Repository.UserRepo;

import java.util.List;

@Service
public class QvtService {
    @Autowired
    QvtRepository qr ;
    @Autowired
    QvtAnswerRepository qar;
    @Autowired
    UserRepo ur;
    @Autowired
    EmailServiceB emailServiceB;


    public void  addQvt(Qvt qvt , Long iduser){
        User user = ur.findById(iduser).get();
        qvt.setTauxDeReponse(0);
        qvt.setUserCretateur(user);
        qr.save(qvt);
    }
    public void responseQvt(Long idQvt, Long iduser, QvtAnswer qvtAnswer){
        List<User>users = (List<User>) ur.findAll();
        User user = ur.findById(iduser).get();
        Qvt qvt = qr.findById(idQvt).get();
        qvtAnswer.setUser(user);
        qvtAnswer.setQuestion(qvt);
        qvt.setTauxDeReponse((qvt.getQVTAnswers().size()/users.size())*100);
        qar.save(qvtAnswer);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Qvt !");
        mailMessage.setFrom("bessem@esprit.tn");
        mailMessage.setText("merci !");
        emailServiceB.sendEmailB(mailMessage);
    }

    public List<QvtAnswer> getAnswerByqvt(Long idQvt){
        Qvt qvt = qr.findById(idQvt).get();
        return qvt.getQVTAnswers();
    }

}
