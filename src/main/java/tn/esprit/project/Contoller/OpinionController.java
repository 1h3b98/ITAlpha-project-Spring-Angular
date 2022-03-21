package tn.esprit.project.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Opinion;
import tn.esprit.project.Service.OpinionService;

import java.util.List;

@RestController
@RequestMapping("/opinion")
public class OpinionController {

    @Autowired
    OpinionService os ;

    @GetMapping("/getOpinionbypost/{id}")
    @ResponseBody
    public List<Opinion> getOpinionByPost(@PathVariable("id")Long idPost){
        return os.getOpinionByPost(idPost);
    };
    @GetMapping("/add/{idu}/{idf}")
    @ResponseBody
    public Opinion add(@RequestBody Opinion opinion,@PathVariable("idu") Long idUser ,@PathVariable("idf") Long idForum){
        return os.add(opinion,idUser,idForum);
    };

    @DeleteMapping("/delete/{id}")
    public void deleteOpinion (@PathVariable("id")Long idOpinion){
        os.deleteOpinion(idOpinion);
    };

    @GetMapping("/update/{id}")
    public Opinion update(@RequestBody Opinion o ,@PathVariable("id") Long idOpinion){
        return os.update(o,idOpinion);
    };
}
