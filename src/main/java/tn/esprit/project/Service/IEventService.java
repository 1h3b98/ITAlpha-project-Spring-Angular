package tn.esprit.project.Service;
import java.io.IOException;
import java.util.List;

import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.User;

public interface IEventService {
 Event join(long eventId, long userId);
 Event removeJoin(long eventId, long userId);
 Event addLike(long eventId);
 Event removeLike(long eventId);
 Event addEvent(Event e) throws IOException;
 Event updateEvent(Event e);
 void deleteEvent(long eventId);
 List<Event> getAllEvent();
 Event findEvent(long eventId);
 List<User> getUsersByEvent(long eventId);
 
}
