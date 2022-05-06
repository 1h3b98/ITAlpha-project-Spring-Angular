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

	 @Query("select a.event from Action a where a.actionType=:at and a.recieverId=:rId ")
    public List<Event> findByInviteAndRecieverAndSender(@Param("at")ActionType invite,@Param("rId")Long recieverId);

    @Query("select a from Action a where a.actionType=:at and a.recieverId=:rId and a.event.eventId=:eId ")
    public Action findInviteByRecieverAndEvent(@Param("at")ActionType invite,@Param("rId")Long recieverId ,@Param("eId")Long eventId);

    @Query("select a from Action a where a.actionType=:LIKEA and a.userAction.userId=:u and a.event.eventId=:eId")
    public Action getLike(@Param("u")Long uId,@Param("eId")Long eId,@Param("LIKEA")ActionType like);

    @Query("select a from Action a where a.actionType=:FAVORITE and a.userAction.userId=:u and a.event.eventId= :eId")
    public Action getFav(@Param("u")Long uId,@Param("eId")Long eId,@Param("FAVORITE")ActionType fav);

    @Query("select a from Action a where a.actionType=:JOIN and a.userAction.userId=:u and a.event.eventId= :eId")
    public Action getJoin(@Param("u")Long uId,@Param("eId")Long eId,@Param("JOIN")ActionType join);

    @Query("select a from Action a where a.actionType=:COMMENT and a.event.eventId= :eId")
    public List<Action> getComment(@Param("eId")Long eId,@Param("COMMENT")ActionType comment);
}
