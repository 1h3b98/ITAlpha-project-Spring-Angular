package tn.esprit.project.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Forum;
import tn.esprit.project.Service.ForumService;
import tn.esprit.project.Service.RatingService;

import java.util.List;

@RestController
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    ForumService fs;
    @Autowired
    RatingService rs ;


/******************************** CRUD *********************************************/
    @GetMapping("/addarticle/{id}")
    @ResponseBody
    public Forum addArticle(@RequestBody Forum f, @PathVariable("id") Long idUser) {
        return fs.addArticle(f,idUser);
    };

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteForum(@PathVariable("id")Long id){
        fs.deleteForum(id);
    };
    @GetMapping("/update/{id}")
    @ResponseBody
    public Forum update(@PathVariable("id") Long idforum){
      return fs.update(idforum);
    };
    @GetMapping("/getall")
    public List<Forum> getAll(){
        return fs.getAll();
    };

    @GetMapping("/getatricles")
    @ResponseBody
    public List<Forum> getArticles(){
        return fs.getArticles();
    }
/*************************** FAVORIS ************************/

    @GetMapping("/addtof/{ida}/{idu}")
    public void addTofavorites(@PathVariable("ida") Long idarticle,@PathVariable("idu") Long idUser){
        fs.addTofavorites(idarticle,idUser);
    }
    @GetMapping("/getf/{idu}")
    public List<Forum> getFavorites(@PathVariable("idu")Long iduser){
       return fs.getFavorites(iduser);
    }

    /*************************** rate ************************/

    @GetMapping("/{idp}/rate/{idu}/{note}")
    @ResponseBody
    public void ratearticle(@PathVariable("note") float note , @PathVariable("idp") Long idform,@PathVariable("idu") Long idUser){
        rs.ratearticle(note,idform,idUser);
    };
}
