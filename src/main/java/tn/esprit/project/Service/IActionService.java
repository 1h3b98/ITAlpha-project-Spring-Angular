package tn.esprit.project.Service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import tn.esprit.project.Entities.Action;


public interface IActionService {
	 Action addAction(Action a,long userId,long eventId);
	 Action acceptInvite(Action a,long actionId,long recieverId,long eventId);
	 void refuseInvite(Action a ,long actionId,long receiverId,long eventId);
	 void deleteLikeOrJoin(long actionId,long eventId,long userId);
	 void deleteAction(long actionId);
	 void deleteOther(long actionId);
	 List<Action> getAllAction();
	 Action findAction(long actionId);
	 Action getInvite(Long recieverId,long userId);
	
	 
}
