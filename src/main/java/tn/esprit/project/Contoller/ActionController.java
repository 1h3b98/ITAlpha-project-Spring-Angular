package tn.esprit.project.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.esprit.project.Entities.Action;
import tn.esprit.project.Entities.Event;
import tn.esprit.project.Service.IActionService;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Action")
public class ActionController {
	@Autowired 
	IActionService ar;
	
	
	@PostMapping("/add/{user_id}/{event_id}")
	@ResponseBody
	public Action addAction(@RequestBody Action a,@PathVariable("user_id") long userId,@PathVariable("event_id") long eventId){
		return ar.addAction(a,userId,eventId);
	}
	@PutMapping("/accept/{action_id}/{reciever_id}/{event_id}")
	public Action acceptInvite(@RequestBody Action a,@PathVariable("action_id") long actionId,@PathVariable("reciever_id") long recieverId,@PathVariable("event_id") long eventId){
		return ar.acceptInvite(a, actionId, recieverId, eventId);
	}
	@DeleteMapping("/refuse/{action_id}/{reciever_id}/{event_id}")
	public void refuseInvite(@RequestBody Action a ,@PathVariable("action_id") long actionId,@PathVariable("reciever_id") long receiverId,@PathVariable("event_id") long eventId){
		ar.refuseInvite(a, actionId, receiverId, eventId);
	}
	@DeleteMapping("/delete/{action_id}/{event_id}/{user_id}")
	public void deleteLikeOrJoin(@PathVariable("action_id") long actionId,@PathVariable("event_id") long eventId,@PathVariable("user_id") long userId){
		ar.deleteLikeOrJoin(actionId, eventId, userId);
	}
	@DeleteMapping("/delete/{action_id}")
	public void deleteOther(@PathVariable("action_id") long actionId){
		ar.deleteOther(actionId);
	}
	@GetMapping("/getallAction")
	public List<Action> getActions(){
		List<Action> listAction=ar.getAllAction();
		return listAction;
	}
	@GetMapping("/getallFavEvent/{user_id}")
	public List<Event> getAllFActions(@PathVariable("user_id") long userId){
		List<Event> listFavEvent=ar.getAllFavAction(userId);
		return listFavEvent;
	}
	
	@GetMapping("/getOneAction/{action_id}")
	public Action getOneAction(@PathVariable("action_id") long actionId){
		return ar.findAction(actionId);
	}
	
	@GetMapping("/getInvite/{reciever_id}")
	public List<Event> getReceiverInvite(@PathVariable("reciever_id") Long recieverId){
		return ar.getAllInvite(recieverId);
	}
	@GetMapping("/getInvite/{reciever_id}/{event_id}")
	public Action getEventInvite(@PathVariable("reciever_id") Long recieverId,@PathVariable("event_id") Long eventId){
		return ar.getInviteByEvent(recieverId,eventId);
	}
	@GetMapping("/getLike/{user_id}/{event_id}")
	public Action getlike(@PathVariable("user_id") Long userId,@PathVariable("event_id") Long eventId){
		return ar.getlike(userId,eventId);
	}

	@GetMapping("/getJoin/{user_id}/{event_id}")
	public Action getjoinByUserAndEvent(@PathVariable("user_id") Long userId,@PathVariable("event_id") Long eventId){
		return ar.getjoin(userId,eventId);
	}
	@GetMapping("/getFav/{user_id}/{event_id}")
	public Action getfavByUserAndEvent(@PathVariable("user_id") Long userId,@PathVariable("event_id") Long eventId){
		return ar.getfav(userId,eventId);
	}
	@GetMapping("/getAllComment/{event_id}")
	public List<Action> getcomment(@PathVariable("event_id") Long eventId){
		return ar.getcomment(eventId);
	}
}
