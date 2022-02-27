package tn.esprit.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Event;

import tn.esprit.project.Repository.EventRepository;

@Service
public class EventServiceImpl implements IEventService {
	@Autowired
	EventRepository er;

	public Event addEvent(Event e){
		er.save(e);
		return e;
	}
	@Override
	public Event updateEvent(Event e){
		Event eventToupdate=er.findById(e.getEventId()).orElse(null);
		eventToupdate.setTitle(e.getTitle());
		eventToupdate.setStartTime(e.getStartTime());
		eventToupdate.setEndTime(e.getEndTime());
		eventToupdate.setTitle(e.getTitle());
		eventToupdate.setImg(e.getImg());
		eventToupdate.setOffers(e.getOffers());
		eventToupdate.setLikenbr(e.getLikenbr());
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
		return er.findById(eventId).get();
	}
	
	

}
