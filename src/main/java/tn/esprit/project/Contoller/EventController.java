package tn.esprit.project.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.project.Entities.Event;
import tn.esprit.project.Service.IEventService;

@RestController
@RequestMapping("/Event")
public class EventController {
	@Autowired 
	IEventService er; 
	
	@PostMapping("/add")
	@ResponseBody
	public Event addEvent(@RequestBody Event e){
		return er.addEvent(e);
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

}
