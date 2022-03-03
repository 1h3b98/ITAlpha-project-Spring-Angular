package tn.esprit.project.Service;

import tn.esprit.project.Entities.Post;

import java.util.List;

public interface IPostService {
    public Post getPost(Long id);
    public List<Post> getPosts();
    public Post addPost(Post p,Long idUser);
    public Post updatePost(Post p);
    public void deletePost(Long id);
    public List<Post> getPostByUser(Long idUser);
}
