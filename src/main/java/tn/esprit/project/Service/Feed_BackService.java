package tn.esprit.project.Service;

import edu.stanford.nlp.*;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.FeedBack;
import tn.esprit.project.Entities.User;
import tn.esprit.nlp.Pipeline;
import tn.esprit.project.Repository.Feed_BackRepository;
import tn.esprit.project.Repository.UserRepository;

import java.util.List;

@Service
public class Feed_BackService {

    @Autowired
    Feed_BackRepository fr;
    @Autowired
    UserRepository  ur;


    public FeedBack add_Feedback(FeedBack f,Long id_reciever,Long id_sender){
        User u =ur.findById(id_reciever).get();
        User us =ur.findById(id_sender).get();
        f.setReciever(u);
        f.setSender(us);
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


    public void FeedbackAnalysis(){

       ur.findAll().forEach(u->{
           //if(fr.existsByReciever(u)){
               StanfordCoreNLP stc=Pipeline.getPipeline();
           //int n2 =(int) coreDocument.sentences().stream().filter(s -> s.sentiment().equals("Positive")).count();
           u.getFeedbackrecieved().stream().map(f -> new CoreDocument(f.getContent())).forEach(coreDocument -> {
               stc.annotate(coreDocument);
               int N = (int) coreDocument.sentences().stream().filter(s -> s.sentiment().equals("Negative")).count();
               System.out.println("test"+coreDocument.sentences().get(0).sentiment());
               if (N > coreDocument.sentences().stream().filter(s -> s.sentiment().equals("Positive")).count()) {
                   u.setPoints(u.getPoints() - 10);
                   ur.save(u);
               } else if (coreDocument.sentences().stream().filter(s -> s.sentiment().equals("Positive")).count() > N) {
                   u.setPoints(u.getPoints() + 10);
                   ur.save(u);
               }
           });//}
       });
    }


}
