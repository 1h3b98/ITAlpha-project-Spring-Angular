package tn.esprit.project.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Post;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Service.PostService;
import tn.esprit.project.Service.RecommandationService;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostContoller {
    @Autowired
    PostService ps;
    @Autowired
    RecommandationService rs ;

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
    @PostMapping("/add/{id}")
    @ResponseBody
    public Post addPost(@RequestBody Post p,@PathVariable("id") Long id) {
        return ps.addPost(p,id);
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
    @GetMapping("/test")
    @ResponseBody
    public List<Post> TesterPostsWithRandomUsers(){
       return rs.TesterPostsWithRandomUsers();
    }
}

