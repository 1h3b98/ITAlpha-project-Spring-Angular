package tn.esprit.project.Service;
import java.util.List;

import tn.esprit.project.Entities.Event;
public interface IEventService {
 Event join(long eventId, long userId);
 Event removeJoin(long eventId, long userId);
 Event addLike(long eventId);
 Event removeLike(long eventId);
 Event addEvent(Event e);
 Event updateEvent(Event e);
 void deleteEvent(long eventId);
 List<Event> getAllEvent();
 Event findEvent(long eventId);
 
}
