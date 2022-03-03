package tn.esprit.project.Service;

import tn.esprit.project.Entities.LikeComment;
import tn.esprit.project.Entities.LikePost;
import tn.esprit.project.Entities.Post;

import java.util.Map;
import java.util.Set;

public interface ILikePostService {
    public LikePost  makelikePost( Long idpost, Long idUser);
    public LikePost  makedislikePost(Long idpost , Long idUser);
    public void removeLike(Long idpost , Long idUser);
    public Map<Post,Integer> getPostLike();
    public Post mostLikedPost();
    public Set<Post> treeWithLike();
    public Set<Post> negativePosts();
    public int getNbLike(Post p);
    public int getNbDeslike(Post post);
    public Map<Post, Integer> getPostDeslike();
}
