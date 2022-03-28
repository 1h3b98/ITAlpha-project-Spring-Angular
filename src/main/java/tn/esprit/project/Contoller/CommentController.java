package tn.esprit.project.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Comment;
import tn.esprit.project.Entities.LikeComment;
import tn.esprit.project.Entities.Post;
import tn.esprit.project.Service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService cs;

    //-------- List Comment-------
    @GetMapping("/getComments/{id}")
    @ResponseBody
    public List<Comment> getCommentsPost(@PathVariable("id")Long id){
        return cs.getCommentsPost(id);
    }

    //-------- add comment-------
    @PostMapping("/addComment/{idu}/{idp}")
    @ResponseBody
    public Comment addComment(@RequestBody Comment c,@PathVariable("idu") Long idu,@PathVariable("idp") Long idp){
        return cs.addComment(c,idu,idp);
    }

    //--------- delete---------
    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable("id") Long id){
        cs.deleteComment(id);
    }

    //--------update----------
    @GetMapping("/update/{id}")
    @ResponseBody
    public Comment updateComment(@RequestBody Comment c, @PathVariable("id")Long idCommment){
        return  cs.updateComment(c,idCommment);
    };
    /**********************************    like    *****************************/


    @GetMapping("/makelikecomment/{id}/{idu}")
    @ResponseBody
    public LikeComment makelikecomment(@PathVariable("id")Long idcomment,@PathVariable("idu") Long idUser){
       return cs.makelikecomment(idcomment, idUser);
    };

    @GetMapping("/makedislikecomment/{id}/{idu}")
    @ResponseBody
    public LikeComment  makedislikecomment(@PathVariable("id")Long idcomment,@PathVariable("idu") Long idUser){
        return cs.makedislikecomment(idcomment, idUser);
    };

    @DeleteMapping("/deleteLike/{id}/{idu}")
    public void removeLike(@PathVariable("id") Long idcomment,@PathVariable("idu") Long idUser){
        cs.removeLike(idcomment,idUser);
    };
}
