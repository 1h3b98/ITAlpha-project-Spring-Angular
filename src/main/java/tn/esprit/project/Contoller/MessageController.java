package tn.esprit.project.Contoller;

import org.springframework.web.bind.annotation.RestController;

import tn.esprit.project.Entities.*;
import tn.esprit.project.Service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping("/Message")
public class MessageController {

	@Autowired
	MessageService messageService;
	@GetMapping("/retrieve-all-Message")
	public List<Message> getAllMessage() {
		List<Message> listMessage = messageService.ShowMessages();
		return listMessage;
	}
	@PostMapping("/add-Message")
	public Message addMessage(@RequestBody Message p) {
		return messageService.AjouterMessage(p);
	}
	@PutMapping("/modify-Message")
	public Message modifyMessage(@RequestBody Message message) {
		return messageService.updateMessage(message);
	}
	@DeleteMapping("/remove-Message/{Message-id}")
	public void removeQuiz(@PathVariable("Message-id") Long Messageid) {
		messageService.DeleteMessage(Messageid);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

