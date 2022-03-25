package tn.esprit.project.Service;

import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;
@Service
@Slf4j

public class NotificationService implements INotificationService  {
	@Autowired
	NotificationRepository ntrepo;
	@Override
	public Notification AjouterNotification(Notification Qz) {
		return ntrepo.save(Qz);

	}

	@Override
	public Notification updateNotification(Notification notification) {
		return ntrepo.save(notification);

	}

	@Override
	public void DeleteNotification(long nt) {
		ntrepo.deleteById(nt);
		
	}

	@Override
	public List<Notification> ShowNotification() {
		List<Notification> notification = null;
		try {
		
			notification = (List<Notification>)ntrepo.findAll();
		for (Notification notif : notification) {
		log.debug(" User : " + notif.toString());
		}
		}
		catch (Exception e) {log.error("Error in retrieveProject : " + e);}
		
		
	return notification;
	}

}
