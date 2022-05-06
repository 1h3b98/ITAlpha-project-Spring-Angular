package tn.esprit.project.Service;


import java.sql.Timestamp;

import java.util.List;
import java.util.Objects;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.project.Entities.Action;
import tn.esprit.project.Entities.ActionType;
import tn.esprit.project.Entities.Event;
import tn.esprit.project.Repository.ActionRepository;
import tn.esprit.project.Repository.EventRepository;

import tn.esprit.project.Repository.UserRepo;

@Service
@Slf4j
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
				p.getActionType().equals(ActionType.LIKEA));
		foundJoin = actionList.stream().anyMatch((Action p) -> p.getEvent().equals(er.findById(eventId).orElse(null)) &&
				p.getUserAction().equals(ur.findById(userId).orElse(null)) &&
				p.getActionType().equals(ActionType.JOINA));
		foundInvite = actionList.stream().anyMatch((Action p) -> Objects.equals(er.findById(eventId).orElse(null), p.getEvent()) &&
				Objects.equals(p.getUserAction(), ur.findById(userId).orElse(null)) && Objects.equals(p.getRecieverId(), a.getRecieverId()) &&
				Objects.equals(p.getActionType(), ActionType.INVITE));
		foundFav = actionList.stream().anyMatch((Action p) -> p.getEvent().equals(er.findById(eventId).orElse(null)) &&
				p.getUserAction().equals(ur.findById(userId).orElse(null)) &&
				p.getActionType().equals(ActionType.FAVORITE));
		
		
		if (a.getActionType()==ActionType.LIKEA){
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
		else if (a.getActionType()==ActionType.JOINA){
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
				actionToupdate.setActionType(ActionType.JOINA);
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
		if(actionToupdate.getActionType()==ActionType.JOINA){
			es.removeJoin(eventId, userId);
			deleteAction(actionId);	
		}
		else if(actionToupdate.getActionType()==ActionType.LIKEA){
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
		List<Event> list=(ur.findById(userId).orElse(null)).getFavEvents();
		return list;
	}
	@Override
	public Action findAction(long actionId){
		return ar.findById(actionId).orElse(null);
	}
	
	@Override
	public List<Event> getAllInvite(Long recieverId){
		ActionType invite=ActionType.INVITE;
		List<Event>allInvite=ar.findByInviteAndRecieverAndSender(invite, recieverId);
	return allInvite;

	}
	@Override
	public Action getInviteByEvent(Long recieverId,Long eventId){
		ActionType invite=ActionType.INVITE;
		Action oneInvite=ar.findInviteByRecieverAndEvent(invite, recieverId,eventId);
		return oneInvite;

	}
	@Override
	public Action getlike(Long uId,Long eId){
		ActionType like=ActionType.LIKEA;
		Action result;
		try{
			Action likes=ar.getLike(uId,eId,like);
			if (likes!=null){
				result=likes;
			}else{
				log.info("done");
				result=null;
			}
		}catch (Exception e){
			e.printStackTrace();
			result = null;
		}
		return result;

	}
	@Override
	public Action getfav(Long uId,Long eId){
		ActionType fav=ActionType.FAVORITE;
		Action result;
		try{
			Action favs=ar.getFav(uId,eId,fav);
			if (favs!=null){
				result=favs;
			}else{
				result=null;
			}
		}catch (Exception e){
			e.printStackTrace();
			result = null;
		}
		return result;


	}
	@Override
	public Action getjoin(Long uId,Long eId){
		ActionType join=ActionType.JOINA;
		Action result;
		try{
			Action joins=ar.getJoin(uId,eId,join);
			if (joins!=null){
				result=joins;
			}else{
				result=null;
			}
		}catch (Exception e){
			e.printStackTrace();
			result = null;
		}
		return result;

	}
	@Override
	public List<Action> getcomment(Long eId){
		ActionType comment=ActionType.COMMENT;
		return ar.getComment(eId,comment);

	}
	
	
}
