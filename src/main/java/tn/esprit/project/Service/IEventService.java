package tn.esprit.project.Service;
import java.util.List;

import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.User;
public interface IEventService {
 Event updateActionEvent(Event e,long actionId);	
 Event join(Event e,User u);	
 Event addEvent(Event e);
 Event updateEvent(Event e);
 void deleteEvent(long eventId);
 List<Event> getAllEvent();
 Event findEvent(long eventId);
 
}
