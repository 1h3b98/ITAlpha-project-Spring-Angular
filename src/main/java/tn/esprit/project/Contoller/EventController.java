package tn.esprit.project.Contoller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Service.IEventService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/Event")
public class EventController {
	@Autowired
    IEventService er;
	
	@PostMapping("/add")
	@ResponseBody
	public Event addEvent(@RequestBody Event e,@RequestParam("file") MultipartFile file) throws IOException {
		return er.addEvent(e,file);
	}
	@PutMapping("/update")
	public Event updateEvent(@RequestBody Event e){
		return er.updateEvent(e);
	}
	
	@DeleteMapping("/delete/{event_id}")
	public void deleteEvent(@PathVariable("event_id") long eventId){
		er.deleteEvent(eventId);
	}

	@GetMapping("/getallEvent")
	public List<Event> getEvents(){
		List<Event> listEvent=er.getAllEvent();
		return listEvent;
	}

	@GetMapping("/getOneEvent/{event_id}")
	public Event getOneEvent(@PathVariable("event_id") long eventId){
		return er.findEvent(eventId);
	}

	@GetMapping("/getUsersByEvent/{event_id}")
	public List<User> getEvents(@PathVariable("event_id") long eventId){
		List<User> listUser=er.getUsersByEvent(eventId);
		return listUser;
	}
}
