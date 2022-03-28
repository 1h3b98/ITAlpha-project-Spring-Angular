package tn.esprit.project.Service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.PubliciteOffre;
import tn.esprit.project.Entities.Rating;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.PubliciteRepo;
import tn.esprit.project.Repository.RatingRepository;
import tn.esprit.project.Repository.UserRepo;
import tn.esprit.project.Response.ResponseHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PubliciteService {
    private final PubliciteRepo publiciteRepo;
    private final UserRepo userRepo;
    private final RatingRepository ratingRepository;
    public ResponseEntity<Object> addOffre(PubliciteOffre publiciteOffre, Long idf) {
        //   if(publiciteOffre.getTitre().length()<=0 && !publiciteOffre.getDatedebut().isBefore(publiciteOffre.getDateexpir())&&publiciteOffre.getDateexpir().isBefore(LocalDateTime.now()))
        try {
            if (publiciteOffre.getTitre().length() <= 0) {
                return ResponseHandler.generateResponse("You should set offer title!", HttpStatus.MULTI_STATUS, publiciteOffre);
            }
            if (!publiciteOffre.getDatedebut().isBefore(publiciteOffre.getDateexpir())) {
                return ResponseHandler.generateResponse("error : dateexpire is < datedebut!", HttpStatus.MULTI_STATUS, publiciteOffre);
            }
            if (publiciteOffre.getDateexpir().isBefore(LocalDateTime.now())) {
                return ResponseHandler.generateResponse("error : dateexpire must be > dcurrent date!", HttpStatus.MULTI_STATUS, publiciteOffre);
            }
            User user = userRepo.findById(idf).get();
            publiciteOffre.setCreatedat(LocalDateTime.now());
            publiciteOffre.setUser(user);
            publiciteRepo.save(publiciteOffre);
            return ResponseHandler.generateResponse("Successfully added offer!", HttpStatus.OK, publiciteOffre);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
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
        publicite1.setCreatedat(LocalDateTime.now());
        publicite1.setDateexpir(publicite.getDateexpir());
        publicite1.setImage(publicite.getImage());
        publicite1.setNum(publicite.getNum());
        publicite1.setMail(publicite.getMail());
        publicite1.setStatus(publicite.getStatus());
        publicite1.setUser(publicite.getUser());
        publicite1.setHappyDay(publicite.getHappyDay());
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
}
