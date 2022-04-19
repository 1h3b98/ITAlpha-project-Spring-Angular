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

	 @Query("select a from Action a where a.actionType=:at and a.recieverId=:rId and a.userAction=:u ")
	    public Action findByInviteAndRecieverAndSender(@Param("at")ActionType invite,@Param("rId")Long recieverId,@Param("u")User user);
}
