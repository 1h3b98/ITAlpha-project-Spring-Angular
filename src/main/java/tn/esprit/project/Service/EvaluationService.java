package tn.esprit.project.Service;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.nlp.Pipeline;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    UserRepo ur;
    @Autowired
    TrophyRepository tr;

    @Autowired
    PostRepository pr;
    @Autowired
    LikePostRepository lr;

    @Autowired
    ActionRepository ar;

    @Autowired
    NotificationRepository nr;
    @Autowired
    EventRepository er;

    @Autowired
    EvaluationRepo Er;

//@Scheduled(cron ="@Weekly")
//@Scheduled(fixedRate = 6000)
public void User_join_quiz(){
    er.findAll().forEach(e->{
        if((e.getEndTime().before(new Date()))&&(e.getEndTime().after(addDays(new Date(),-7))))
         ar.findusersByEvent(e, ActionType.JOINA).forEach(u->{

            u.setPoints(u.getPoints()+e.getEventReward()+u.getScoree().stream().filter(s->s.getDate()
                    .before(new Date()) && s.getDate().after(addDays(new Date(),-7)))
                            .mapToInt(Score::getUserscore).sum());
             Notification n = new Notification();
             n.setUser(u);
             n.setContent(u.getPoints()+e.getEventReward()+u.getScoree().stream().filter(s->s.getDate()
                             .before(new Date()) && s.getDate().after(addDays(new Date(),-7)))
                     .mapToInt(Score::getUserscore).sum()+"are added to your points");
             n.setNotDate(new Timestamp(new Date().getTime()));
             nr.save(n);
            ur.save(u);
        });
    });

}

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    //@Scheduled(cron = "@weekly")
    @Transactional
//@Scheduled(fixedRate = 6000)
    public void evaluation_like_post_Comment()
    { List<User> users= (List<User>) ur.findAll();
        for(User user : users){

            float points = user.getPoints();
            List<Post> postscreated =user.getPosts();

            int nbrp =postscreated.size();
            List<Float> tauxfinal= new ArrayList<>() ;
            if(!postscreated.isEmpty()) {
                AtomicInteger nb= new AtomicInteger();
                postscreated.forEach(p->{
                    if((p.getCreateAt().before(new Date()))&&(p.getCreateAt().after(addDays(new Date(),-7)))) {
                        nb.getAndIncrement();
                        int lnb = (int) lr.countnblikebypost(p);
                        int dnb = (int) lr.countnbdeslikebypost(p);
                        int taux = (lnb * 100) / (lnb + dnb);
                        int tauxn = (dnb * 100) / (lnb + dnb);

                        tauxfinal.add((float) ((taux - tauxn) - CommentPostEvaluation(p))/2);
                        System.out.println(tauxfinal.get(0));
                        System.out.println(CommentPostEvaluation(p));

                    }});//end for
                float somme = 0;
                for (Float f : tauxfinal) {
                    somme += f;
                }
                Float Final = somme / nbrp;
                System.out.println(Final);
                System.out.println(nb.intValue());

                user.setPoints((int) (points + ((Final * (nb.intValue()/nbrp) ) )));
                Notification n = new Notification();
                n.setUser(user);
                n.setContent((int) (points + ((Final * (nb.intValue()/nbrp) ) ))+"are added to your points");
                n.setNotDate(new Timestamp(new Date().getTime()));
                n.setStatus(true);
                nr.save(n);
                 ur.save(user);

            }
        }
    }

  public int CommentPostEvaluation(Post p){
    AtomicInteger n = new AtomicInteger();
      AtomicInteger neg = new AtomicInteger();
      AtomicInteger pos = new AtomicInteger();
      AtomicInteger ss = new AtomicInteger();
        StanfordCoreNLP stc=Pipeline.getPipeline();
        //int n2 =(int) coreDocument.sentences().stream().filter(s -> s.sentiment().equals("Positive")).count();
        p.getComments().stream().map(f -> new CoreDocument(f.getContent())).forEach(coreDocument -> {
            stc.annotate(coreDocument);
            int N = (int) coreDocument.sentences().stream().filter(s -> s.sentiment().equals("Negative")).count();
            if (N > coreDocument.sentences().stream().filter(s -> s.sentiment().equals("Positive")).count()) {
                 neg.getAndAdd(N * 100 / coreDocument.sentences().size()) ;
                 ss.getAndAdd( coreDocument.sentences().size());

            } else{
                pos.getAndAdd( (int) coreDocument.sentences().stream()
                        .filter(s -> s.sentiment().equals("Positive")).count()* 100 / coreDocument.sentences().size()) ;
                ss.getAndAdd( coreDocument.sentences().size());
            }
            n.getAndAdd(neg.intValue()- pos.intValue());
        });
      return n.intValue()/ ss.intValue();


  }


    public Map<Long, Long> triByPoints(){
        Map<Long, Long> m  = new HashMap<Long,Long>();
        ur.findAll().forEach(u-> {
            m.put(u.getUserId(), (long) u.getPoints());
        });
        return m.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
    //@Scheduled(fixedRate = 6000)
    public void attribute_Badges(){
        int nb=triByPoints().size();
        int index=1;
        Notification n = new Notification();
        for (Map.Entry entry : triByPoints().entrySet()) {
            if   ((int)((index*100))/nb<=10){
                Badgebronze(ur.findById((Long) entry.getKey()).get());
                index++;
                ur.save(ur.findById((Long) entry.getKey()).get());
                n.setUser(ur.findById((Long) entry.getKey()).get());
                n.setContent("you have recieved a Bronze Badge!");
                n.setNotDate(new Timestamp(new Date().getTime()));
                    nr.save(n);
            }

            else if  (((int)((index*100))/nb>10)&&((int)((index*100))/nb<70)){
               Badgesilver(ur.findById((Long) entry.getKey()).get());
                index++;
                ur.save( ur.findById((Long) entry.getKey()).get());
                n.setUser(ur.findById((Long) entry.getKey()).get());
                n.setContent("you have recieved a Bronze Badge!");
                n.setNotDate(new Timestamp(new Date().getTime()));
                nr.save(n);
            }
            else {
                Badgegold(ur.findById((Long) entry.getKey()).get());
                ur.save( ur.findById((Long) entry.getKey()).get());
                n.setUser(ur.findById((Long) entry.getKey()).get());
                n.setContent("you have recieved a Bronze Badge!");
                n.setNotDate(new Timestamp(new Date().getTime()));
                nr.save(n);
            }

        }
    }

    //@Scheduled(fixedRate = 6000)
    public void Badgebronze(User u){

            if(!Er.existsEvaluationByUser(u)){
            Evaluation e = new Evaluation();
            e.setBadge(Badge.BRONZE);
            e.setUser(u);
            e.setDescription("congratulation  "+u.getFirstName()+"!  you won a bronze badge !");
            Er.save(e);}
            else{
                Evaluation e = u.getEvaluation();
                e.setBadge(Badge.BRONZE);
                e.setDescription("congratulation "+u.getFirstName()+"!  you won a bronze badge !");
                Er.save(e);}


    }

    public void Badgesilver(User u){

                if(!Er.existsEvaluationByUser(u)){
                    Evaluation e = new Evaluation();
                    e.setBadge(Badge.SILVER);
                    e.setUser(u);
                    e.setDescription("congratulation "+u.getFirstName()+"! you won a silver badge !");
                    Er.save(e);}
                else{
                    Evaluation e = u.getEvaluation();
                    e.setBadge(Badge.SILVER);
                    e.setDescription("congratulation "+u.getFirstName()+"! you won a silver badge !");
                    Er.save(e);
                }

    }

    public void Badgegold(User u){

                if(!Er.existsEvaluationByUser(u)){
                    Evaluation e = new Evaluation();
                    e.setBadge(Badge.GOLDEN);
                    e.setUser(u);
                    e.setDescription("congratulation "+u.getFirstName()+"! you won a golden badge !");
                    Er.save(e);}
                else{
                    Evaluation e = u.getEvaluation();
                    e.setBadge(Badge.GOLDEN);
                    e.setDescription("congratulation "+u.getFirstName()+"! you won a golden badge !");
                    Er.save(e);
                }

    }

    //                           *************Employee of the week ************
    //@Scheduled(fixedRate = 6000)
    //@Scheduled(cron = "0 0 0 * * 0")
    public void  EmployeeOfTheWeek(){
       List<User> users = new ArrayList<User>();
       List<Evaluation> es=Er.findAll().stream().filter(e->e.getBadge().equals(Badge.GOLDEN)).collect(Collectors.toList());
        es.forEach(e->{
            users.add(e.getUser());
        });


        User u = users.stream().max(Comparator.comparingInt(user -> user.getPoints())).orElse(null);
        Trophy t = new Trophy();
        t.setDateTrophey(new Date());
        t.setTrophytype(TrophyType.Week);
        t.setEvaluation(u.getEvaluation());
        t.setDescription("congratulation "+u.getFirstName()+"!  you are the employee of the week");
        tr.save(t);
        Notification n = new Notification();
        n.setUser(u);
        n.setContent(t.getDescription());
        n.setNotDate(new Timestamp(new Date().getTime()));
        nr.save(n);
}

    //                           *************Employee of the month ************
    //@Scheduled(cron = "0 0 0 L * *")
    @Transactional
    //@Scheduled(fixedRate = 6000)
    public void  EmployeeOfTheMonth(){
    Map<Long,Long> m = new HashMap<Long,Long>();
    ur.findAll().forEach(f->{
        AtomicInteger nb= new AtomicInteger();
        if (Er.existsEvaluationByUser(f) && (tr.existsTrophiesByEvaluation(f.getEvaluation())) ) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int monthnow = cal.get(Calendar.MONTH);
            f.getEvaluation().getTrophies().forEach(t->{
                Calendar call = Calendar.getInstance();
                call.setTime(t.getDateTrophey());
                int month = call.get(Calendar.MONTH);
                if (month==monthnow && t.getTrophytype().equals(TrophyType.Week)){
                    nb.getAndIncrement();
                }
            });
            m.put(f.getUserId(), nb.longValue());
        }
    });
            Long idU=m.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();

            Trophy t = new Trophy();
            t.setDateTrophey(new Date());
            t.setTrophytype(TrophyType.Month);
            t.setEvaluation(ur.findById(idU).get().getEvaluation());
            t.setDescription("congratulation "+ur.findById(idU).get().getFirstName()+"!  you are the employee of the month");
            tr.save(t);
        Notification n = new Notification();
        n.setUser(ur.findById(idU).get());
        n.setContent(t.getDescription());
        n.setNotDate(new Timestamp(new Date().getTime()));
        nr.save(n);

    }



    //                           *************Employee of the year ************
    @Transactional
    //@Scheduled(fixedRate = 6000)
    //@Scheduled(cron = "0 0 0 31 12 *")
    public void  EmployeeOfTheYear(){
        Map<Long,Long> m = new HashMap<Long,Long>();
        ur.findAll().forEach(f->{
            AtomicInteger nb= new AtomicInteger();
            if (Er.existsEvaluationByUser(f) && (tr.existsTrophiesByEvaluation(f.getEvaluation())) ) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                int yearhnow = cal.get(Calendar.YEAR);
                f.getEvaluation().getTrophies().forEach(t->{
                    Calendar call = Calendar.getInstance();
                    call.setTime(t.getDateTrophey());
                    int year = call.get(Calendar.YEAR);
                    if (year==yearhnow && t.getTrophytype().equals(TrophyType.Month)){
                        nb.getAndIncrement();
                    }
                });
                m.put(f.getUserId(), nb.longValue());
            }
        });
        Long idU=m.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();

        Trophy t = new Trophy();
        t.setDateTrophey(new Date());
        t.setTrophytype(TrophyType.Year);
        t.setEvaluation(ur.findById(idU).get().getEvaluation());
        t.setDescription("congratulation "+ur.findById(idU).get().getFirstName()+"!  you are the employee of the year");
        tr.save(t);
        Notification n = new Notification();
        n.setUser(ur.findById(idU).get());
        n.setContent(t.getDescription());
        n.setNotDate(new Timestamp(new Date().getTime()));
        nr.save(n);
    }


    


}

