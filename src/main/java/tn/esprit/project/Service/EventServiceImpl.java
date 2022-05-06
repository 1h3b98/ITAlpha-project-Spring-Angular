package tn.esprit.project.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;

@Service
@Transactional
public class EventServiceImpl implements tn.esprit.project.Service.IEventService {
	@Autowired
	EventRepository er;
	@Autowired
    UserRepo ur;
	@Autowired
	ActionRepository ar;
	@Autowired
	GoogleCalendarService gcs;
	@Autowired
	private EventDbRepository eventDbRepository;

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
	public Event addEvent(Event e,MultipartFile file) throws IOException {
		e.setJoinnbr(0);
		e.setLikenbr(0);
		er.save(e);
		gcs.addEventToCalendar(e);
		storefileEvent(file,e);
		return e;
	}
	@Override
	public Event updateEvent(Event e){
		Event eventToupdate=er.findById(e.getEventId()).orElse(null);
		assert eventToupdate != null;
		eventToupdate.setTitle(e.getTitle());
		eventToupdate.setAbout(e.getAbout());
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

	@Override
	public List<User> getUsersByEvent(long eventId){
		return er.findUserListByEvent(eventId);
	}


	/************************************ file **********************************/

	public EventResponse storefileEvent(MultipartFile file, Event event) throws IOException {
		String fileName = file.getOriginalFilename();
		EventDb eventDb = new EventDb(UUID.randomUUID().toString(), fileName, file.getContentType(), file.getBytes(), event);
		eventDbRepository.save(eventDb);
		return  mapToEventResponse(eventDb);
	}

	public EventDb getFileById(String id) {

		Optional<EventDb> fileOptional = eventDbRepository.findById(id);

		if(fileOptional.isPresent()) {
			return fileOptional.get();
		}
		return null;
	}

	public List<EventResponse> getFileList(){
		return eventDbRepository.findAll().stream().map(this::mapToEventResponse).collect(Collectors.toList());
	}

	private EventResponse mapToEventResponse(EventDb eventDb) {
		return new EventResponse(eventDb.getId(), eventDb.getType(), eventDb.getName(), eventDb.getEvent());
	}


}

