package tn.esprit.project.Service;

import java.util.List;

import tn.esprit.project.Entities.Action;
import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.User;


public interface IActionService {
	 
	 
	 Action addAction(Action a,long userId,long eventId);
	 Action acceptInvite(Action a,long actionId,long recieverId,long eventId);
	 void refuseInvite(Action a ,long actionId,long receiverId,long eventId);
	 void deleteLikeOrJoin(long actionId,long eventId,long userId);
	 void deleteAction(long actionId);
	 void deleteOther(long actionId);
	 List<Action> getAllAction();
	 List<Event> getAllFavAction(long userId);
	 Action findAction(long actionId);
	 Action getInvite(Long recieverId,long userId);
	
	 
}
