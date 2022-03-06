package tn.esprit.project.Service;


import java.sql.Timestamp;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.project.Entities.Action;
import tn.esprit.project.Entities.ActionType;
import tn.esprit.project.Service.IEventService;
import tn.esprit.project.Repository.ActionRepository;
import tn.esprit.project.Repository.EventRepository;
import tn.esprit.project.Repository.UserRepo;

@Service
public class ActionServiceImpl implements IActionService {
	@Autowired
	ActionRepository ar;
	@Autowired
	UserRepo ur;
	@Autowired
	EventRepository er;
	@Autowired
	IEventService es;
	@Override
	public Action addAction(Action a,long userId,long eventId){
		a.setUserAction(ur.findById(userId).get());
		a.setEvent(er.findById(eventId).get());
		a.setTime(new Timestamp(System.currentTimeMillis()));
		if (a.getActionType()==ActionType.LIKE){
			a.setComment(null);
			a.setLikeStatus(true);
			a.setJoinStatus(false);	
			a.setRecieverId(null);
			a.setAccepted(false);
			es.addLike(eventId);
			

		}
		else if (a.getActionType()==ActionType.JOIN){
			a.setComment(null);
			a.setLikeStatus(false);
			a.setJoinStatus(true);
			a.setRecieverId(null);
			a.setAccepted(true);
			es.join(eventId,userId);
			
		}
		else if (a.getActionType()==ActionType.INVITE){
			a.setComment(null);
			a.setLikeStatus(false);
			a.setJoinStatus(false);
			a.setRecieverId(a.getRecieverId());
			a.setAccepted(false);

		}
		else if (a.getActionType()==ActionType.COMMENT){
			a.setComment(a.getComment());
			a.setLikeStatus(false);
			a.setJoinStatus(false);
			a.setRecieverId(null);
			a.setAccepted(false);
		}
		else if (a.getActionType()==ActionType.REPONSE){
			a.setComment(null);
			a.setLikeStatus(false);
			a.setJoinStatus(true);
			a.setRecieverId(null);
			a.setAccepted(a.isAccepted());
			
		}
		ar.save(a);
		//es.updateActionEvent(a.getEvent(),a.getActionId());
		return a;
	}
	@Override
	public Action acceptInvite(Action a ,long actionId,long receiverId,long eventId){
		Action actionToupdate=ar.findById(actionId).orElse(null);
		if(actionToupdate.getActionType()==ActionType.INVITE){
			
			if(a.getActionType()==ActionType.REPONSE && a.isAccepted()==true){
				actionToupdate.setUserAction(ur.findById(receiverId).get());
				actionToupdate.setActionType(ActionType.JOIN);
				actionToupdate.setComment(null);
				actionToupdate.setLikeStatus(false);
				actionToupdate.setJoinStatus(true);
				actionToupdate.setRecieverId(null);
				actionToupdate.setAccepted(true);
				es.join(eventId,receiverId);
			}
		}
		return ar.save(actionToupdate);
	}
	
	@Override
	public void refuseInvite(Action a ,long actionId,long receiverId,long eventId){
		Action actionToupdate=ar.findById(actionId).orElse(null);
		if(actionToupdate.getActionType()==ActionType.INVITE){
			
			if(a.getActionType()==ActionType.REPONSE && a.isAccepted()==false){
				deleteAction(actionId);
			}
		}
	}
	@Override
	public void deleteLikeOrJoin(long actionId,long eventId,long userId){
		Action actionToupdate=ar.findById(actionId).orElse(null);
		if(actionToupdate.getActionType()==ActionType.JOIN){
			es.removeJoin(eventId, userId);
			deleteAction(actionId);	
		}
		else if(actionToupdate.getActionType()==ActionType.LIKE){
			es.removeLike(eventId);
			deleteAction(actionId);
		}
		
	}
	
	@Override
	public void deleteOther(long actionId){
		Action actionToupdate=ar.findById(actionId).orElse(null);
		if(actionToupdate.getActionType()==ActionType.COMMENT||actionToupdate.getActionType()==ActionType.REPONSE){
	    deleteAction(actionId);
		}
		
	}
	@Override
	public void deleteAction(long actionId){
		
		ar.deleteById(actionId);
		
		
	}
	@Override
	public List<Action> getAllAction(){
		return (List<Action>) ar.findAll();
	}
	@Override
	public Action findAction(long actionId){
		return ar.findById(actionId).get();
	}
}
