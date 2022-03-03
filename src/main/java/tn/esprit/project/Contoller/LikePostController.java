package tn.esprit.project.Contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.LikePost;
import tn.esprit.project.Entities.Post;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.PostRepository;
import tn.esprit.project.Service.LikePostService;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/likepost")
public class LikePostController {
    @Autowired
    LikePostService rs ;
    @Autowired
    PostRepository pr;

    @PostMapping("/{idu}/like/{idp}")
    @ResponseBody
    public LikePost makeLike(@PathVariable("idu")Long  userId, @PathVariable("idp") Long postId) {
        return rs.makelikePost(userId,postId);
    }

    @PostMapping("/{idu}/dislike/{idp}")
    @ResponseBody
    public LikePost makeDislike(@PathVariable("idu")Long userId, @PathVariable("idp")Long postId) {
        return rs.makedislikePost(userId,postId);
    }

    @DeleteMapping("/{idu}/remove/{idp}")
    @ResponseBody
    public void removeLike(@PathVariable("idu")Long userId,@PathVariable("idp") Long postID) {
        rs.removeLike(userId,postID);
    }

    @GetMapping("/mostliked")
    @ResponseBody
    public Post mostLikedPost() {
        return rs.mostLikedPost();
    }

    @GetMapping("/tree")
    @ResponseBody
    public Set<Post> treeWithLike() {
        return rs.treeWithLike();
    }

    @GetMapping("/negative")
    @ResponseBody
    public Set<Post> negativePosts() {
        return rs.negativePosts();
    }

    @GetMapping("/nblike/{idP}")
    @ResponseBody
    public int getNbLike(@PathVariable("idP") long id) {
        Post post=pr.findById(id).get() ;
        if(post!=null)
            return rs.getNbLike(post);
        return 0;
    }
    @GetMapping("/nbDeslike/{idP}")
    @ResponseBody
    public int getNbDesLike(@PathVariable("idP") long id) {
        Post post=pr.findById(id).get() ;
        if(post!=null)
            return rs.getNbDeslike(post);
        return 0;
    }
    @GetMapping("/post/like")
    @ResponseBody
    public Map< Post,Integer> getPostLike() {
        return rs.getPostLike();
    }
    @GetMapping("/post/deslike")
    @ResponseBody
    public Map< Post,Integer> getPostDesLike() {
        return rs.getPostLike();
    }
}
