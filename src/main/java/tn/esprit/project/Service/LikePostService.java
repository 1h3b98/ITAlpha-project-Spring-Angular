package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.LikePost;
import tn.esprit.project.Entities.LikePostId;
import tn.esprit.project.Entities.Post;
import tn.esprit.project.Repository.LikePostRepository;
import tn.esprit.project.Repository.PostRepository;
import tn.esprit.project.Repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LikePostService implements ILikePostService{

    @Autowired
    LikePostRepository lp ;
    @Autowired
    UserRepository ur ;
    @Autowired
    PostRepository pr ;
    @Override
    public LikePost makelikePost(Long idpost, Long idUser) {
        LikePost likep = new LikePost();
        LikePostId likeId = new LikePostId();
        likeId.setUser(ur.findById(idUser).orElse(null));
        likeId.setPost(pr.findById(idpost).orElse(null));
        likep.setLikepostId(likeId);
        likep.setValue(true);
        return lp.save(likep);
    }

    @Override
    public LikePost  makedislikePost(Long idpost, Long idUser) {
        LikePost likep = new LikePost();
        LikePostId likeId = new LikePostId();
        likeId.setUser(ur.findById(idUser).orElse(null));
        likeId.setPost(pr.findById(idpost).orElse(null));
        likep.setLikepostId(likeId);
        likep.setValue(false);
        return lp.save(likep);
    }

    @Override
    public void removeLike(Long idpost, Long idUser) {
        LikePostId likeId = new LikePostId();
      likeId.setPost(pr.findById(idpost).orElse(null));
      likeId.setUser(ur.findById(idUser).orElse(null));
      lp.deleteById(likeId);
    }

    @Override
    public Map<Post, Integer> getPostLike() {
        Map<Post,Integer> map=new HashMap<>();
        Set<Post> posts=lp.getReactedPosts();
        posts.forEach(p->map.put(p,lp.countnblikebypost(p)));
        return map;
    }

    @Override
    public Post mostLikedPost() {
        Set<Post> posts=lp.getReactedPosts();
        return  posts.stream().max((p,v)->lp.countnblikebypost(p)-lp.countnblikebypost(v)).orElse(null);
    }

    @Override
    public Set<Post> treeWithLike() {
        Set<Post> posts=lp.getReactedPosts();
        return  posts.stream().sorted((v,p)->lp.countnblikebypost(p)-lp.countnbdeslikebypost(v)).collect(Collectors.toSet());
    }

    @Override
    public Set<Post> negativePosts() {
        Set<Post> posts=lp.getReactedPosts();
        return posts.stream().filter(p->lp.countnblikebypost(p)<lp.countnbdeslikebypost(p)).collect(Collectors.toSet());
    }

    @Override
    public int getNbLike(Post p) {
        if(lp.countnblikebypost(p)!=0)
            return  lp.countnblikebypost(p);
        return 0;
    }

    @Override
    public int getNbDeslike(Post post) {
        if(lp.countnbdeslikebypost(post)!=0)
            return lp.countnbdeslikebypost(post);
        return 0;
    }

    @Override
    public Map<Post, Integer> getPostDeslike() {
        Map<Post,Integer> map=new HashMap<>();
        Set<Post> posts=lp.getReactedPosts();
        posts.forEach(p->map.put(p,lp.countnbdeslikebypost(p)));
        return map;
    }
}
