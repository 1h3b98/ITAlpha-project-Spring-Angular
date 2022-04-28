package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.*;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action,Long> {
    @Query("select a.userAction from Action a where a.event=:event  and a.actionType=:JOIN")
    public List<User> findusersByEvent(@Param("event") Event e, @Param("JOIN")ActionType join);

	 @Query("select a from Action a where a.actionType=:at and a.recieverId=:rId ")
    public List<Action> findByInviteAndRecieverAndSender(@Param("at")ActionType invite,@Param("rId")Long recieverId);

    @Query("select a from Action a where a.actionType=:LIKE and a.userAction.userId=:uId and a.event.eventId=:eId")
    public Action getLike(@Param("u")Long uId,@Param("eId")Long eId,@Param("LIKE")ActionType like);

    @Query("select a from Action a where a.actionType=:FAVORITE and a.userAction.userId=:u and a.event.eventId= :eId")
    public Action getFav(@Param("u")Long uId,@Param("eId")Long eId,@Param("FAVORITE")ActionType fav);

    @Query("select a from Action a where a.actionType=:JOIN and a.userAction.userId=:u and a.event.eventId= :eId")
    public Action getJoin(@Param("u")Long uId,@Param("eId")Long eId,@Param("JOIN")ActionType join);

    @Query("select a from Action a where a.actionType=:COMMENT and a.event.eventId= :eId")
    public Action getComment(@Param("eId")Long eId,@Param("COMMENT")ActionType comment);
}
