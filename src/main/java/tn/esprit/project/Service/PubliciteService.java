package tn.esprit.project.Service;


import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.PubliciteOffre;
import tn.esprit.project.Entities.Rating;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.PubliciteRepo;
import tn.esprit.project.Repository.RatingRepository;
import tn.esprit.project.Repository.UserRepo;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PubliciteService {


    private final PubliciteRepo publiciteRepo;
    private final UserRepo userRepo;




    private final RatingRepository ratingRepository;
    public PubliciteOffre addOffre(PubliciteOffre publiciteOffre, Long idu) {

            User user = userRepo.findById(idu).get();
            publiciteOffre.setCreatedat(new Date());
            publiciteOffre.setUser(user);
           return publiciteRepo.save(publiciteOffre);
    }
    public void DeletePub(Long id) {
        publiciteRepo.deleteById(id);
    }

    public  List<PubliciteOffre> getall() {
        return publiciteRepo.findAll();
    }

    public List<PubliciteOffre> getByTtile(String title) {
        List<PubliciteOffre> userList = publiciteRepo.findPubliciteByTitre(title);
        return userList;
    }

    public PubliciteOffre Update(PubliciteOffre publicite, Long id) {
        PubliciteOffre publicite1 = publiciteRepo.findById(id).get();
        publicite1.setCible(publicite.getCible());
        publicite1.setDatedebut(publicite.getDatedebut());
        publicite1.setCreatedat(new Date());
        publicite1.setDateexpir(publicite.getDateexpir());
        publicite1.setImage(publicite.getImage());
        publicite1.setNum(publicite.getNum());
        publicite1.setMail(publicite.getMail());
        publicite1.setStatus(publicite.getStatus());
        publicite1.setUser(publicite.getUser());
        return publiciteRepo.save(publicite1);
    }

        public void Rating(Long note, Long idPost, Long idUser) {
        User user = userRepo.findById(idUser).get();
        PubliciteOffre publiciteOffre = publiciteRepo.findById(idPost).get();
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setPubliciteOffre(publiciteOffre);
        rating.setValue(note);
        ratingRepository.save(rating);
   }
   public void DeleteRating(Long id){
        ratingRepository.deleteById(id);

   }

    public void removeRating(Rating rating) {
        ratingRepository.delete(rating);
    }
//   public List<PubliciteOffre> Getpuboffre() {
//        List<PubliciteOffre> publiciteOffreList = (List<PubliciteOffre>) publiciteRepo.findAll();
//        List<PubliciteOffre> publiciteOffreList1= new ArrayList<>();
//        for (PubliciteOffre p:publiciteOffreList) {
//            p.setRating(ratingRepository.Sommedenotebyoffre(p)/ratingRepository.nbredoffre(p));
//            publiciteOffreList1.add(p);
//            publiciteRepo.save(p);
//       }
//       return publiciteOffreList1;
//    }
         public Long meiilellll(){
        List<Rating> list =ratingRepository.findAll();
       list.sort((u,v)-> (int) (u.getValue()-v.getValue()));
       return list.get(0).getValue();
         }

}
