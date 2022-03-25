package tn.esprit.project.Contoller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping("/Notification")

public class NotificationController {
	@Autowired
	NotificationService notificationS;
	
	@GetMapping("/retrieve-all-Notification")
	public List<Notification> getAllQuizq() {
		List<Notification> listNotification = notificationS.ShowNotification();
		return listNotification;
	}
	@PostMapping("/add-Notification")
	public Notification addQuiz(@RequestBody Notification p) {
		return notificationS.AjouterNotification(p);
	}
	@PutMapping("/modify-Notification")
	public Notification modifyNotification(@RequestBody Notification notif) {
		return notificationS.updateNotification(notif);
	}
	@DeleteMapping("/remove-Quiz/{Notification-id}")
	public void DeleteNotification(@PathVariable("/Notification-id") Long quizid) {
		notificationS.DeleteNotification(quizid);
	}
	
	
	
	
	
	
	
	
	
}
