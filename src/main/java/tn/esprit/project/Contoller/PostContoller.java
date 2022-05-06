package tn.esprit.project.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.PostRepository;
import tn.esprit.project.Repository.UserRepo;
import tn.esprit.project.Service.PostService;
import tn.esprit.project.Service.RecommandationService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/post")
public class PostContoller {
    @Autowired
    PostService ps;
    @Autowired
    RecommandationService p;
    @Autowired
    UserRepo ur;
    @Autowired
    PostRepository pr;

    //------post------------
    @GetMapping("/get/{id}")
    @ResponseBody
    public Post getPost(@PathVariable("id") Long id) {
        return ps.getPost(id);
    }

    //-------get postes-------
    @GetMapping("/get")
    @ResponseBody
    public List<Post> getPosts() {
        return (List<Post>) ps.getPosts();
    }

    //--------add--------------
    @PostMapping("/add/{id}/{content}")
    @ResponseBody
    public FileResponse addPost(@PathVariable("content") String content, @PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return ps.addPost(content,id, file);
    }

    //--------update-----------
    @GetMapping("/update/{id}")
    @ResponseBody
    public Post updatePost(@RequestBody  Post p,@PathVariable("id")Long id) {
        return ps.updatePost(p,id);
    }

    //----------delete---------
    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        ps.deletePost(id);
    }

    //-----------get-----------
    @GetMapping("/getbyuser/{id}")
    @ResponseBody
    public List<Post>  getPostByUser(@PathVariable("id") Long idUser){
        return ps.getPostByUser(idUser);
    }

    //----------test-----------
/* List<Post> TesterPostsWithRandomUsers(){
       return rs.TesterPostsWithRandomUsers();
    }

    @GetMapping("/gett")
    @ResponseBody
    public List<Post> getNewPosts(){
        return rs.getNewPosts();
    }
*/
    /**************************** MEMORIS *******************************/
    @GetMapping("/getMemories/{id}")
    @ResponseBody
    public List<Post> getmemories(@PathVariable("id") Long id){
        return ps.getmemories(id);
    }

    /********************** SIGNALER ****************************************/
    @PostMapping("/{idf}/{idu}")
    public void SignalerForum(@RequestBody Signaler signaler , @PathVariable("idf") Long idForum , @PathVariable("idu") Long iduser){
        ps.SignalerPost(signaler,idForum,iduser);
    }


    /***************************recommandatin****************************/

    @GetMapping("/score/{ida}/{idb}")
    @ResponseBody
    public double scoreWithEuclideanEistance(@PathVariable("ida")Long usera, @PathVariable("idb")Long userb){
        return p.scoreWithEuclideanEistance(usera,userb);
    }
    @GetMapping("/similar")
    @ResponseBody
    public void similarUser(){
        p.similarUser();
    }
    @GetMapping("/similare/{idu}")
    @ResponseBody
    public List<Forum> similarUsersbyuser(@PathVariable("idu")Long user){
        User user1 = ur.findById(user).get();
        return p.similarUsersbyuser(user1);
    };
    /***************************** LIKE ************************************/

    @PostMapping("/{idu}/like/{idp}")
    @ResponseBody
    public LikePost makeLike(@PathVariable("idu")Long  userId, @PathVariable("idp") Long postId) {
        return ps.makelikePost(userId,postId);
    }

    @PostMapping("/{idu}/dislike/{idp}")
    @ResponseBody
    public LikePost makeDislike(@PathVariable("idu")Long userId, @PathVariable("idp")Long postId) {
        return ps.makedislikePost(userId,postId);
    }

    @DeleteMapping("/{idu}/remove/{idp}")
    @ResponseBody
    public void removeLike(@PathVariable("idu")Long userId,@PathVariable("idp") Long postID) {
        ps.removeLike(userId,postID);
    }

    @GetMapping("/mostliked")
    @ResponseBody
    public Post mostLikedPost() {
        return ps.mostLikedPost();
    }

    @GetMapping("/tree")
    @ResponseBody
    public Set<Post> treeWithLike() {
        return ps.treeWithLike();
    }

    @GetMapping("/negative")
    @ResponseBody
    public Set<Post> negativePosts() {
        return ps.negativePosts();
    }

    @GetMapping("/nblike/{idP}")
    @ResponseBody
    public int getNbLike(@PathVariable("idP") long id) {
        Post post=pr.findById(id).get() ;
        if(post!=null)
            return ps.getNbLike(post);
        return 0;
    }
    @GetMapping("/nbDeslike/{idP}")
    @ResponseBody
    public int getNbDesLike(@PathVariable("idP") long id) {
        Post post=pr.findById(id).get() ;
        if(post!=null)
            return ps.getNbDeslike(post);
        return 0;
    }

    @GetMapping("/post/like")
    @ResponseBody
    public Map< Post,Integer> getPostLike() {
        return ps.getPostLike();
    }


    @GetMapping("/post/deslike")
    @ResponseBody
    public Map< Post,Integer> getPostDesLike() {
        return ps.getPostLike();
    }

}

