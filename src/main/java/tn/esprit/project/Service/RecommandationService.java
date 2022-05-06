package tn.esprit.project.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecommandationService {

    @Autowired
    PostRepository pr ;
    @Autowired
    UserRepo ur ;
    @Autowired
    LikePostRepository lpr ;
    @Autowired
    ModelRespository mr ;
    @Autowired
    ForumRepository fr;
    @Autowired
    RatinggRepository rr;
    @Autowired
    SimilarRepository sr;
    /*
    //--------------------------------------------
    public Map<Post,Integer> getPostByLikes(){
        Map<Post,Integer> map=new HashMap<>();
        Set<Post> posts=lpr.getReactedPosts();
        posts.forEach(p->map.put(p,lpr.countnbdeslikebypost(p)));
        return map;
    }

    //----Prends des publications avec une marge d'une heure

    public List<Post> getNewPosts(){
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(30));
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(-30));
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(timestamp1);
        c2.setTime(timestamp2);
        int ct = (int) timestamp1.getTime();
        int ct2 = (int) timestamp2.getTime();
        List<Post> postes = (List<Post>) pr.findAll();
        log.info(String.valueOf(timestamp1));
        List<Post> p1 = new ArrayList<>();
        for (Post p : postes) {
            log.info(String.valueOf(p.getCreateAt()));
            int e = (int) p.getCreateAt().getTime();
            if(e<ct && e>ct2){
                p1.add(p);
            }

        }
        return p1;
           }

    //-----testé l'interaction des utilisateurs pendant une période devait une heure et calculer le taux.

    public List<Post> TesterPostsWithRandomUsers(){
        List<User> users = (List<User>) ur.findAll();
        List<User> usersModel = new ArrayList<>();
        List<Post> Postes = getNewPosts();
        Random r = new Random();
        int low = 1;
        int high = users.size();
        log.info(String.valueOf(Postes.size()));

        for (int i=0;i<Postes.size();i++){
                int result = r.nextInt(high-low) + low;
            log.info(String.valueOf(result));
            Model model = new Model();
                    model.setIdUser(result);
                    model.setIdPost(Postes.get(i).getIdPost());
                    model.setCode(1);
                    mr.save(model);
                }
        return Postes ;
           // usersModel= users.stream().filter(e->e.getUserId()==result).collect(Collectors.toList());
        }

    //------------------accueil------------------

    public List<Post> accueilNews(Long iduser){
        List<Model> modeles = (List<Model>) mr.findAll();
        List<Post> postsFinal =new ArrayList<>();
        modeles.stream().filter(e->e.getIdUser()==iduser)
                .forEach(model -> postsFinal.add(pr.findById(model.getIdPost()).get()));



    return  null;
    }
*/
    /************************** espace forum ***************************/

    public void similarUser(){
        List<User> users = (List<User>) ur.findAll();
        sr.deleteAll();
        for (int i =0 ;i<users.size();i++) {
            for (int j =i+1 ;j<users.size();j++) {
                double score = scoreWithEuclideanEistance(users.get(i).getUserId(),users.get(j).getUserId());

                SimilarU similarU =new SimilarU();
                similarU.setIduser1(users.get(i).getUserId());
                similarU.setIduser2(users.get(j).getUserId());
                similarU.setScore(score);
                sr.save(similarU);
            }
            
        }
    }



    public  double scoreWithEuclideanEistance(Long usera,Long userb){
        User user1 = ur.findById(usera).get();
        User user2 = ur.findById(userb).get();

        List<Forum> forums = (List<Forum>) fr.findAll();
        List<Ratingg> ratinggs = (List<Ratingg>) rr.findAll();
        double score1=0;
        double score2=0;
        double somme=0;
        double scorefinale=0;
        int i=0;
        Set<Long> forumset= new TreeSet<>();

        for (Ratingg r: ratinggs) {forumset.add(r.getRatingId().getForm().getIdForum());}

        for (Long l : forumset) {
            i++;
            for (Ratingg r: ratinggs) {
                if(r.getRatingId().getForm().getIdForum()== l &&
                        r.getRatingId().getUser().getUserId()==user1.getUserId()){
                    score1=r.getValue()+10;
                    break;
            }else score1 = 10;
        }
            for (Ratingg r: ratinggs) {
                if(r.getRatingId().getForm().getIdForum()== l &&
                        r.getRatingId().getUser().getUserId()==user2.getUserId()){
                    score2=r.getValue()+10;
                    break;
                }else score2 = 10;
            }
            somme=(score1-score2)/10;
            scorefinale+=sqr(somme) ;
            log.info("score1 : " + String.valueOf(score1));
            log.info("score2 : " + String.valueOf(score2));
            log.info("somme : " + String.valueOf(somme));
            log.info("final : " + String.valueOf(scorefinale));
            log.info("------------------------");

        }
        return 1/(1+(Math.sqrt(scorefinale)));
    }


    static public double sqr(double a) {
        return a*a;
    }

    /********************************************************************************************/
    public List<Forum> similarUsersbyuser(User user){
        List<SimilarU> similarUList = (List<SimilarU>) sr.findAll();
        Set<SimilarU> foo = new HashSet<SimilarU>(similarUList);
        List<SimilarU> triList= new ArrayList<>();
        List<Forum> finallist= new ArrayList<>();
        int i=0;
        int i1=0;
        triList= foo.stream().sorted(Comparator.comparingDouble(SimilarU::getScore).reversed())
               .filter(x1->x1.getScore()<=1).collect(Collectors.toList());
        for (SimilarU s: triList) {
            if (s.getIduser1()== user.getUserId()){
                User user1= ur.findById(s.getIduser2()).get();
                finallist.addAll(user1.getForums());

            }
            if (s.getIduser2()== user.getUserId()){
                User user2= ur.findById(s.getIduser1()).get();
                finallist.addAll( user2.getForums());
            }

        }
        return finallist;
           }


}
