package tn.esprit.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.ActionRepository;
import tn.esprit.project.Repository.EventRepository;
import tn.esprit.project.Repository.UserRepo;

@Service
public class EventServiceImpl implements IEventService {
	@Autowired
	EventRepository er;
	@Autowired
	UserRepo ur;
	@Autowired
	ActionRepository ar;

	@Override
	public Event join(long eventId, long userId){
		User userInevent=ur.findById(userId).orElse(null);
		Event eventToupdate=er.findById(eventId).orElse(null);
		assert eventToupdate != null;
		eventToupdate.getUserL().add(userInevent);
		eventToupdate.setJoinnbr(eventToupdate.getJoinnbr()+1);		
		return er.save(eventToupdate);
	}
	@Override
	public Event removeJoin(long eventId, long userId){
		User userInevent=ur.findById(userId).orElse(null);
		Event eventToupdate=er.findById(eventId).orElse(null);
		assert eventToupdate != null;
		eventToupdate.getUserL().remove(userInevent);
		eventToupdate.setJoinnbr(eventToupdate.getJoinnbr()-1);		
		return er.save(eventToupdate);
	}
	@Override
	public Event addLike(long eventId){
		Event eventToupdate=er.findById(eventId).orElse(null);
		assert eventToupdate != null;
		eventToupdate.setLikenbr(eventToupdate.getLikenbr()+1);
		return er.save(eventToupdate);
	}
	
	@Override
	public Event removeLike(long eventId){
		Event eventToupdate=er.findById(eventId).orElse(null);
		assert eventToupdate != null;
		eventToupdate.setLikenbr(eventToupdate.getLikenbr()-1);
		return er.save(eventToupdate);
	}
	
	@Override
	public Event addEvent(Event e){
		e.setJoinnbr(0);
		e.setLikenbr(0);
		er.save(e);
		return e;
	}
	@Override
	public Event updateEvent(Event e){
		Event eventToupdate=er.findById(e.getEventId()).orElse(null);
		assert eventToupdate != null;
		eventToupdate.setTitle(e.getTitle());
		eventToupdate.setStartTime(e.getStartTime());
		eventToupdate.setEndTime(e.getEndTime());
		eventToupdate.setTitle(e.getTitle());
		eventToupdate.setImg(e.getImg());
		eventToupdate.setEventReward(e.getEventReward());
		return er.save(eventToupdate);
	}
	@Override
	public void deleteEvent(long eventId){
		er.deleteById(eventId);
	}
	@Override
	public List<Event> getAllEvent(){
		return (List<Event>) er.findAll();
	}
	@Override
	public Event findEvent(long eventId){
		return er.findById(eventId).orElse(null);
	}
	
	

}
