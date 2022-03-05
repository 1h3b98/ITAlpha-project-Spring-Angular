package tn.esprit.project.Service;

import java.time.LocalDateTime;
import java.util.List;

import javax.sql.rowset.Joinable;

import org.hibernate.query.criteria.internal.JoinImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.project.Entities.Action;
import tn.esprit.project.Entities.ActionType;
import tn.esprit.project.Entities.Event;
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
	
	public Action addAction(Action a,long userId,long eventId){
		
		
		a.setUserAction(ur.findById(userId).get());
		a.setEvent(er.findById(eventId).get());
		a.setTime(LocalDateTime.now());
		
		if (a.getActionType()==ActionType.LIKE){
			a.setComment(null);
			a.setLikeStatus(true);
			a.setJoinStatus(false);
			
		}
		else if (a.getActionType()==ActionType.JOIN){
			a.setComment(null);
			a.setLikeStatus(false);
			a.setJoinStatus(true);
			es.join(a.getEvent(),a.getUserAction());
		}
		else if (a.getActionType()==ActionType.INVITE){
			a.setComment(null);
			a.setLikeStatus(false);
			a.setJoinStatus(false);
		}
		else if (a.getActionType()==ActionType.COMMENT){
			a.setComment(a.getComment());
			a.setLikeStatus(false);
			a.setJoinStatus(false);
			
			
		}
		
		ar.save(a);
		es.updateActionEvent(a.getEvent(),a.getActionId());
		return a;
	}
	@Override
	public Action updateAction(Action a){
		Action actionToupdate=ar.findById(a.getActionId()).orElse(null);
		actionToupdate.setActionType(a.getActionType());
		actionToupdate.setLikeStatus(a.isLikeStatus());
		actionToupdate.setComment(a.getComment());
		actionToupdate.setJoinStatus(a.isJoinStatus());
		
		return ar.save(actionToupdate);
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
