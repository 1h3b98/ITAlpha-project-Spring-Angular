package tn.esprit.project.Service;
import java.util.List;

import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.User;
public interface IEventService {
 Event join(long eventId,long userId);	
 Event addEvent(Event e);
 Event updateEvent(Event e);
 void deleteEvent(long eventId);
 List<Event> getAllEvent();
 Event findEvent(long eventId);
 
}
