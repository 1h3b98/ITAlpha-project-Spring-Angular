package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.project.Entities.*;

@Repository
public interface ActionRepository extends CrudRepository<Action,Long> {
	 @Query("select a from Action a where a.actionType=:at and a.recieverId=:rId and a.userAction=:u ")
	    public Action findByInviteAndRecieverAndSender(@Param("at")ActionType invite,@Param("rId")Long recieverId,@Param("u")User user);
}
