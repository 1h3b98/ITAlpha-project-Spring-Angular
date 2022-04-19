package com.example.pispring.Controller;

import com.example.pispring.Entities.PubliciteOffre;
import com.example.pispring.Entities.Rating;
import com.example.pispring.Repository.RatingRepository;
import com.example.pispring.Service.PubliciteService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PubliciteController {



    private final PubliciteService publiciteService;
    private final RatingRepository ratingRepository;


    @GetMapping("GetMeilleurOffre")
    @Query("select r.publiciteOffre.titre from Rating  r where (r.value)=(select max(r.value) from Rating r)")
    public String bestoffre() {
        return ratingRepository.bestoffre();
    }


    @DeleteMapping("DeleteRating/{id}")
    public void DeleteRating(@PathVariable("id") Long id) {
        publiciteService.DeleteRating(id);
    }
    @PostMapping("addofre/{idf}")
    public PubliciteOffre addOffre(@RequestBody PubliciteOffre publiciteOffre, @PathVariable("idf") Long idu) {
        return publiciteService.addOffre(publiciteOffre, idu);
    }
    @PostMapping("addrating/{note}/{idp}/{idu}")
    public void Rating(@PathVariable("note") Long note,@PathVariable("idp") Long idPost,@PathVariable("idu") Long idUser) {
        publiciteService.Rating(note, idPost, idUser);
    }
    @DeleteMapping("deleterating")
    public void removeRating(@RequestBody Rating rating) {
        publiciteService.removeRating(rating);
    }



    @DeleteMapping("DeletePub/{id}")
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

    @PutMapping("/Updatpublicite/{idpub}")
    public PubliciteOffre Update(@RequestBody PubliciteOffre publicite,@PathVariable("idpub") Long idpub) {

        return publiciteService.Update(publicite,idpub);
    }


}
