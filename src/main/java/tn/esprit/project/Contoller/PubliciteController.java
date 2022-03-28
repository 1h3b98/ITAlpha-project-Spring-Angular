package tn.esprit.project.Contoller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.PubliciteOffre;
import tn.esprit.project.Entities.Rating;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Service.PubliciteService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PubliciteController {
    public ResponseEntity<Object> addOffre(PubliciteOffre publiciteOffre, Long idf) {
        return publiciteService.addOffre(publiciteOffre, idf);
    }
    @PostMapping("addrating/{note}/{idp}/{idu}")
    public void Rating(@PathVariable("note") Long note,@PathVariable("idp") Long idPost,@PathVariable("idu") Long idUser) {
        publiciteService.Rating(note, idPost, idUser);
    }
    @DeleteMapping("deleterating")
    public void removeRating(@RequestBody Rating rating) {
        publiciteService.removeRating(rating);
    }

    private final PubliciteService publiciteService;



//    @PostMapping("addpub/{idf}")
//    public PubliciteOffre add(@RequestBody PubliciteOffre publiciteOffre, @PathVariable("idf") Long idf) {
//        return publiciteService.addOffre(publiciteOffre, idf);
//    }

    @DeleteMapping("DeletePub/{Id}")
    public void DeletePub(@PathVariable("id") Long id) {

        publiciteService.DeletePub(id);
    }

    @GetMapping("/GetAllpub")
    public List<PubliciteOffre> getall() {
        return publiciteService.getall();
    }

    @GetMapping("/getPubBYtitle/{titre}")
    public List<PubliciteOffre> getByTtile(@PathVariable("titre") String title) {
        return publiciteService.getByTtile(title);
    }

    @PutMapping("Updatpublicite/{pub}/{id}")
    public PubliciteOffre Update(@PathVariable("pub") PubliciteOffre publicite, @PathVariable("id") Long id) {

        return publiciteService.Update(publicite, id);
    }


}
