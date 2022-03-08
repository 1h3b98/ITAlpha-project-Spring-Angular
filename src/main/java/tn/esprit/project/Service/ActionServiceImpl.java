package tn.esprit.project.Service;


import java.sql.Timestamp;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.project.Entities.Action;
import tn.esprit.project.Entities.ActionType;
import tn.esprit.project.Entities.User;
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
		List<Action> actionList=getAllAction();
		boolean foundLike = actionList.stream().anyMatch(p -> p.getEvent().equals(er.findById(eventId).get())&& 
				p.getUserAction().equals(ur.findById(userId).get())&& 
				p.getActionType().equals(ActionType.LIKE));
		boolean foundJoin = actionList.stream().anyMatch(p -> p.getEvent().equals(er.findById(eventId).get())&& 
				p.getUserAction().equals(ur.findById(userId).get())&& 
				p.getActionType().equals(ActionType.JOIN));
		boolean foundInvite = actionList.stream().anyMatch(p -> p.getEvent().equals(er.findById(eventId).get())&& 
				p.getUserAction().equals(ur.findById(userId).get())&& p.getRecieverId().equals(a.getRecieverId())&&
				p.getActionType().equals(ActionType.INVITE));
		
		
		if (a.getActionType()==ActionType.LIKE){
			if (foundLike==false){
				
			a.setUserAction(ur.findById(userId).get());
			a.setEvent(er.findById(eventId).get());
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setComment(null);
			a.setLikeStatus(true);
			a.setJoinStatus(false);	
			a.setRecieverId(null);
			a.setAccepted(false);
			es.addLike(eventId);
			ar.save(a);
			}
			else{
				a.setActionType(null);
			}

		}
		else if (a.getActionType()==ActionType.JOIN){
			if (foundJoin==false){
			a.setUserAction(ur.findById(userId).get());
			a.setEvent(er.findById(eventId).get());
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setComment(null);
			a.setLikeStatus(false);
			a.setJoinStatus(true);
			a.setRecieverId(null);
			a.setAccepted(true);
			es.join(eventId,userId);
			ar.save(a);
			}
			else{
				a.setActionType(null);
			}
		}
		else if (a.getActionType()==ActionType.INVITE){
			if (foundInvite==false){
			a.setUserAction(ur.findById(userId).get());
			a.setEvent(er.findById(eventId).get());
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setComment(null);
			a.setLikeStatus(false);
			a.setJoinStatus(false);
			a.setRecieverId(a.getRecieverId());
			a.setAccepted(false);
			ar.save(a);
			}
			else{
				a.setActionType(null);
			}
		}
		else if (a.getActionType()==ActionType.COMMENT){
			a.setUserAction(ur.findById(userId).get());
			a.setEvent(er.findById(eventId).get());
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setComment(a.getComment());
			a.setLikeStatus(false);
			a.setJoinStatus(false);
			a.setRecieverId(null);
			a.setAccepted(false);
			ar.save(a);
		}
		else if (a.getActionType()==ActionType.REPONSE){
			a.setUserAction(ur.findById(userId).get());
			a.setEvent(er.findById(eventId).get());
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setComment(null);
			a.setLikeStatus(false);
			a.setJoinStatus(true);
			a.setRecieverId(null);
			a.setAccepted(a.isAccepted());
			ar.save(a);
			
		}
		
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
	
	@Override
	public Action getInvite(Long recieverId,long userId){
		ActionType invite=ActionType.INVITE;
		User u=ur.findById(userId).get();
		Action inviteAction=ar.findByInviteAndRecieverAndSender(invite,recieverId,u);
		return inviteAction;
	}
	
	
	
}
