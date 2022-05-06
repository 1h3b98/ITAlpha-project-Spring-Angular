package tn.esprit.project.Service;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.FeedBack;
import tn.esprit.project.Entities.Notification;
import tn.esprit.project.Entities.User;
import tn.esprit.nlp.Pipeline;
import tn.esprit.project.Repository.Feed_BackRepository;
import tn.esprit.project.Repository.NotificationRepository;
import tn.esprit.project.Repository.UserRepo;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static tn.esprit.project.Service.EvaluationService.addDays;

@Service
public class Feed_BackService {

    @Autowired
    Feed_BackRepository fr;
    @Autowired
    UserRepo ur;
    @Autowired
    NotificationRepository nr;


    public FeedBack add_Feedback(FeedBack f,Long id_reciever,Long id_sender){
        User u =ur.findById(id_reciever).get();
        User us =ur.findById(id_sender).get();
        f.setReciever(u);
        f.setSender(us);
        f.setDate(new Date());
        return fr.save(f);
    }

    public List<FeedBack> show_Feedbacks_recieved(Long id_reciever){
        User u =ur.findById(id_reciever).get();
        return u.getFeedbackrecieved();
    }

    public int nbr_feedbacks_recieved(Long id_reciever){
        User u =ur.findById(id_reciever).get();
        return u.getFeedbackrecieved().size();

    }
    @Transactional
    @Scheduled(fixedRate = 6000)
    //@Scheduled(cron = "@Weekly")
    public void FeedbackAnalysis(){

       ur.findAll().forEach(u->{
           if(fr.existsByReciever(u)){
               StanfordCoreNLP stc=Pipeline.getPipeline();
           //int n2 =(int) coreDocument.sentences().stream().filter(s -> s.sentiment().equals("Positive")).count();
           u.getFeedbackrecieved().stream()
                   .filter(f->f.getDate().before(new Date())&&(f.getDate().after(addDays(new Date(),-7)))).
                   map(f -> new CoreDocument(f.getContent())).forEach(coreDocument -> {
               stc.annotate(coreDocument);
               int N = (int) coreDocument.sentences().stream().filter(s -> s.sentiment().equals("Negative")).count();
               System.out.println("test"+coreDocument.sentences().get(0).sentiment());
               if (N > coreDocument.sentences().stream().filter(s -> s.sentiment().equals("Positive")).count()) {
                   u.setPoints(u.getPoints() - 10);
                   ur.save(u);
                   Notification n = new Notification();
                   n.setUser(u);
                   n.setStatus(true);
                   n.setContent("-10 are added to your points");
                   n.setNotDate(new Timestamp(new Date().getTime()));
                   nr.save(n);
               } else if (coreDocument.sentences().stream().filter(s -> s.sentiment().equals("Positive")).count() > N) {
                   u.setPoints(u.getPoints() + 10);
                   ur.save(u);
                   Notification n = new Notification();
                   n.setUser(u);
                   n.setStatus(true);
                   n.setContent("10 are added to your points");
                   n.setNotDate(new Timestamp(new Date().getTime()));
                   nr.save(n);
               }
           });}
       });
    }


}
