package tn.esprit.project.Service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.project.Entities.FileResponse;
import tn.esprit.project.Entities.LikePost;
import tn.esprit.project.Entities.Post;
import tn.esprit.project.Entities.Signaler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IPostService {
    public Post getPost(Long id);
    public List<Post> getPosts();
    public FileResponse addPost(String Content, Long idUser, MultipartFile file) throws IOException;
    public Post updatePost(Post p, Long id);
    public void deletePost(Long id);
    public List<Post> getPostByUser(Long idUser);
    public void SignalerPost(Signaler signaler , Long idpost , Long iduser);
    public LikePost makelikePost(Long idpost, Long idUser);
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
