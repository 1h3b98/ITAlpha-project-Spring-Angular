package tn.esprit.project.Service;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.project.Entities.*;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;
@Service
@Slf4j

public class NotificationService implements INotificationService  {
	@Autowired
	NotificationRepository ntrepo;
	@Autowired
	UserRepository userrepo;
	@Override
	public Notification AjouterNotification(Notification Qz) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
Qz.setNotDate(timestamp);
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

	@Override
	public Notification sendnotif(Notification notif, long iduser) {
User user=userrepo.findById(iduser).get();
notif.setUser(user);
Timestamp timestamp = new Timestamp(System.currentTimeMillis());
notif.setNotDate(timestamp);

return ntrepo.save(notif);
	}

	@Override
	public List<Notification> usernotifs(long iduser) {
		List<Notification> notifs=ntrepo.shownotif(iduser);
		return notifs;
	}

}


