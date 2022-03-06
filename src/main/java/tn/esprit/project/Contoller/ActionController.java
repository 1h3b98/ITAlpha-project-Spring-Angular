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

import tn.esprit.project.Entities.Action;
import tn.esprit.project.Service.IActionService;
@RestController
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
		ar.deleteLikeOrJoin(actionId, eventId, userId);;
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
	
	@GetMapping("/getOneAction/{action_id}")
	public Action getOneAction(@PathVariable("action_id") long actionId){
		return ar.findAction(actionId);
	}
}
