package tn.esprit.project.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.PubliciteOffre;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.PubliciteRepo;
import tn.esprit.project.Repository.UserRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class PubliciteService {
    private final PubliciteRepo publiciteRepo;
    private final UserRepo userRepo;
     public PubliciteOffre addOffre(PubliciteOffre publiciteOffre,Long idf){
         User user = userRepo.findById(idf).get();
         publiciteOffre.setUser(user);
         return publiciteRepo.save(publiciteOffre);
     }
    public void DeletePub(Long id){

        publiciteRepo.deleteById(id);
    }
    public List<PubliciteOffre> getall(){

        return   publiciteRepo.findAll();
    }
    public List<PubliciteOffre> getByTtile(String title){

        List<PubliciteOffre> userList =publiciteRepo.findPubliciteByTitre(title);

        return  userList;
    }
    public PubliciteOffre Update(PubliciteOffre publicite, Long id){
        PubliciteOffre publicite1=publiciteRepo.findById(id).get();
        publicite1.setCible(publicite.getCible());
        publicite1.setDatedebut(publicite.getDatedebut());
        publicite1.setDatefin(publicite.getDatefin());
        publicite1.setImage(publicite.getImage());
        publicite1.setNum(publicite.getNum());
        publicite1.setMail(publicite.getMail());
        publicite1.setStatus(publicite.getStatus());
        publicite1.setUser(publicite.getUser());
        publicite1.setHappyDay(publicite.getHappyDay());
        return publiciteRepo.save(publicite1);
    }

}
