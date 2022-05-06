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
	@GetMapping("/retrieveallMessagebetween12/{id1}/{id2}")
	public List<Message> getAllMessagebetween2users(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2) {
		List<Message> listMessage =null;
		listMessage=messageService.ShowMessagesbetween2user(id1,id2);
		return listMessage;
	}
	@PostMapping("/add-Message/{idsender}/{idreceiver}")
	public Message addMessage(@RequestBody Message p,@PathVariable("idsender") Long idsender,@PathVariable("idreceiver") Long idreceiver) {
		return messageService.AjouterMessage(p,idsender,idreceiver);
	}
	@PutMapping("/modify-Message")
	public Message modifyMessage(@RequestBody Message message) {
		return messageService.updateMessage(message);
	}
	@DeleteMapping("/remove-Message/{Message-id}")
	public void removeQuiz(@PathVariable("Message-id") Long Messageid) {
		messageService.DeleteMessage(Messageid);
	}

	@GetMapping("/retrievefinalMessagebetween12/{id1}/{id2}")
	public Message getfinalMessagebetween2users(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2) {

		Message messages=messageService.between2users(id1,id2);
		return messages;
	}

	@GetMapping("/triMessage/{id1}")
	public List<Message> getAllMessagebetween2users(@PathVariable("id1") Long id1) {
		List<Message> listMessage =null;
		listMessage=messageService.triMessage(id1);
		return listMessage;
	}

	@GetMapping("/tri/{id1}")
	public List<Message> triMessageusers(@PathVariable("id1") Long id1) {
		List<Message> listMessage =null;
		listMessage=messageService.triMessage(id1);
		return listMessage;
	}

	@GetMapping("/triuserfinal/{id1}")
	public List<User> trimessagefinal(@PathVariable("id1") Long id1) {
		List<User> listMessage =null;
		listMessage=messageService.listUsermessage(id1);
		return listMessage;
	}
	@GetMapping("/shuser/{id1}")
	public User shuserr(@PathVariable("id1") Long id1) {
		User	listMessage=messageService.ShowUser(id1);
		return listMessage;

	}


}

