package tn.esprit.project.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Forum;
import tn.esprit.project.Entities.Post;
import tn.esprit.project.Entities.Rating;
import tn.esprit.project.Repository.PostRepository;
import tn.esprit.project.Repository.RatingRepository;
import tn.esprit.project.Service.RatingService;

@RestController
@RequestMapping("/RatingPost")
public class RatingController {

    @Autowired
    PostRepository pr;
    @Autowired
    RatingService rs ;

    @PostMapping("/{idp}/rate/{idu}/{note}")
    @ResponseBody
    public void ratePost(@PathVariable("note") int note , @PathVariable("idp") Long idform,@PathVariable("idu") Long idUser){
        rs.ratePost(note,idform,idUser);
    };

    @PostMapping("/update/{idu}/rate/{note}")
    @ResponseBody
    public void updateRating(@PathVariable("note")int note, @RequestBody Forum forum, @PathVariable("idu")  Long idUser){

    };

}
