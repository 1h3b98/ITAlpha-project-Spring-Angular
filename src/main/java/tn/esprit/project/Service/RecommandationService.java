package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Model;
import tn.esprit.project.Entities.Post;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.LikePostRepository;
import tn.esprit.project.Repository.ModelRespository;
import tn.esprit.project.Repository.PostRepository;
import tn.esprit.project.Repository.UserRepository;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RecommandationService {

    @Autowired
    PostRepository pr ;
    @Autowired
    UserRepository ur ;
    @Autowired
    LikePostRepository lpr ;
    @Autowired
    ModelRespository mr ;


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
        List<Post> postes = (List<Post>) pr.findAll();
        return postes.stream().filter(e->e.getCreateAt().before(timestamp1) && e.getCreateAt().after(timestamp2)).collect(Collectors.toList());
    }

    //-----testé l'interaction des utilisateurs pendant une période devait une heure et calculer le taux.

    public List<Post> TesterPostsWithRandomUsers(){
        List<User> users = (List<User>) ur.findAll();
        List<User> usersModel = new ArrayList<>();
        List<Post> Postes = getNewPosts();
        Random r = new Random();
        int low = 1;
        int high = Postes.size();
        for (int i=1;i<high;i++){
                int result = r.nextInt(high-low) + low;
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

    public List<Post> accueilNews(){
    return  null;
    }
}
