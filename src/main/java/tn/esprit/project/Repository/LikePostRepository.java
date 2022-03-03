package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import tn.esprit.project.Entities.*;

import java.util.List;
import java.util.Set;

public interface LikePostRepository extends CrudRepository<LikePost, LikePostId> {

    @Query("select count(R) from LikePost R  where R.value=true and R.likepostId.post= :post")
    public Integer countnblikebypost(@Param("post") Post post);

    @Query("select count(R) from LikePost R  where R.value=false and R.likepostId= :post")
    public Integer countnbdeslikebypost(@Param("post") Post post);

    @Query("select R.likepostId.post from LikePost R")
    Set<Post> getReactedPosts();

    @Query("select R.likepostId.user from LikePost R")
    List<User> getActiveUsers();

}
