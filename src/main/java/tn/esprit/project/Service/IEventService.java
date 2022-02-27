package tn.esprit.project.Service;
import java.util.List;

import tn.esprit.project.Entities.Event;
public interface IEventService {
 Event addEvent(Event e);
 Event updateEvent(Event e);
 void deleteEvent(long eventId);
 List<Event> getAllEvent();
 Event findEvent(long eventId);
 
}
