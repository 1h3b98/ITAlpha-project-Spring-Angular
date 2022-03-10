package tn.esprit.project.Service;


import java.sql.Timestamp;

import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.project.Entities.Action;
import tn.esprit.project.Entities.ActionType;
import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.User;
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
	@Autowired
	UserService us;
	
	
	
	
	boolean foundLike;
	boolean foundJoin;
	boolean foundInvite;
	boolean foundFav;
	Action p;

	{
		p = new Action();
	}

	@Override
	public Action addAction(Action a,long userId,long eventId){
		List<Action> actionList = getAllAction();

		foundLike = actionList.stream().anyMatch((Action p) -> p.getEvent().equals(er.findById(eventId).orElse(null)) &&
				p.getUserAction().equals(ur.findById(userId).orElse(null)) &&
				p.getActionType().equals(ActionType.LIKE));
		foundJoin = actionList.stream().anyMatch((Action p) -> p.getEvent().equals(er.findById(eventId).orElse(null)) &&
				p.getUserAction().equals(ur.findById(userId).orElse(null)) &&
				p.getActionType().equals(ActionType.JOIN));
		foundInvite = actionList.stream().anyMatch((Action p) -> Objects.equals(er.findById(eventId).orElse(null), p.getEvent()) &&
				Objects.equals(p.getUserAction(), ur.findById(userId).orElse(null)) && Objects.equals(p.getRecieverId(), a.getRecieverId()) &&
				Objects.equals(p.getActionType(), ActionType.INVITE));
		foundFav = actionList.stream().anyMatch((Action p) -> p.getEvent().equals(er.findById(eventId).orElse(null)) &&
				p.getUserAction().equals(ur.findById(userId).orElse(null)) &&
				p.getActionType().equals(ActionType.FAVORITE));
		
		
		if (a.getActionType()==ActionType.LIKE){
			if (!foundLike){

			a.setUserAction(ur.findById(userId).orElse(null));
			a.setEvent(er.findById(eventId).orElse(null));
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setComment(null);
			a.setLikeStatus(true);
			a.setFavStatus(false);
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
			if (!foundJoin){
			a.setUserAction(ur.findById(userId).orElse(null));
			a.setEvent(er.findById(eventId).orElse(null));
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setComment(null);
			a.setLikeStatus(false);
			a.setFavStatus(false);
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
			if (!foundInvite){
			a.setUserAction(ur.findById(userId).orElse(null));
			a.setEvent(er.findById(eventId).orElse(null));
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setComment(null);
			a.setLikeStatus(false);
			a.setFavStatus(false);
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
			a.setUserAction(ur.findById(userId).orElse(null));
			a.setEvent(er.findById(eventId).orElse(null));
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setComment(a.getComment());
			a.setLikeStatus(false);
			a.setFavStatus(false);
			a.setJoinStatus(false);
			a.setRecieverId(null);
			a.setAccepted(false);
			ar.save(a);
		}
		else if (a.getActionType()==ActionType.REPONSE){
			a.setUserAction(ur.findById(userId).orElse(null));
			a.setEvent(er.findById(eventId).orElse(null));
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setComment(null);
			a.setLikeStatus(false);
			a.setFavStatus(false);
			a.setJoinStatus(true);
			a.setRecieverId(null);
			a.setAccepted(a.isAccepted());
			ar.save(a);
			
		}
		else if (a.getActionType()==ActionType.FAVORITE){
			if (!foundFav){
				
			a.setUserAction(ur.findById(userId).orElse(null));
			a.setEvent(er.findById(eventId).orElse(null));
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setComment(null);
			a.setLikeStatus(false);
			a.setFavStatus(true);
			a.setJoinStatus(false);	
			a.setRecieverId(null);
			a.setAccepted(false);
			us.addFav(eventId, userId);
			ar.save(a);
			}
			else{
				a.setActionType(null);
			}

		}
		
		
		//es.updateActionEvent(a.getEvent(),a.getActionId());
		return a;
		}
		
	
	
		
	@Override
	public Action acceptInvite(Action a ,long actionId,long receiverId,long eventId){
		Action actionToupdate=ar.findById(actionId).orElse(null);
		assert actionToupdate != null;
		if(actionToupdate.getActionType()==ActionType.INVITE){
			
			if(a.getActionType()==ActionType.REPONSE && a.isAccepted()){
				actionToupdate.setUserAction(ur.findById(receiverId).orElse(null));
				actionToupdate.setActionType(ActionType.JOIN);
				actionToupdate.setComment(null);
				actionToupdate.setLikeStatus(false);
				actionToupdate.setFavStatus(false);
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
		assert actionToupdate != null;
		if(actionToupdate.getActionType()==ActionType.INVITE){
			
			if(a.getActionType()==ActionType.REPONSE && !a.isAccepted()){
				deleteAction(actionId);
			}
		}
	}
	@Override
	public void deleteLikeOrJoin(long actionId,long eventId,long userId){
		Action actionToupdate=ar.findById(actionId).orElse(null);
		assert actionToupdate != null;
		if(actionToupdate.getActionType()==ActionType.JOIN){
			es.removeJoin(eventId, userId);
			deleteAction(actionId);	
		}
		else if(actionToupdate.getActionType()==ActionType.LIKE){
			es.removeLike(eventId);
			deleteAction(actionId);
		}
		else if(actionToupdate.getActionType()==ActionType.FAVORITE){
			us.removeFav(eventId, userId);
			deleteAction(actionId);
		}
		
	}
	
	@Override
	public void deleteOther(long actionId){
		Action actionToupdate=ar.findById(actionId).orElse(null);
		assert actionToupdate != null;
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
	public List<Event> getAllFavAction(long userId){
		return ur.findAllFavEventByUser(userId);
	}
	@Override
	public Action findAction(long actionId){
		return ar.findById(actionId).orElse(null);
	}
	
	@Override
	public Action getInvite(Long recieverId,long userId){
		ActionType invite=ActionType.INVITE;
		User u=ur.findById(userId).orElse(null);
		return ar.findByInviteAndRecieverAndSender(invite,recieverId,u);
	}
	
	
	
}
