package tn.esprit.project.Service;
import java.util.List;
import tn.esprit.project.Entities.*;
public interface INotificationService {
	 Notification AjouterNotification(Notification Qz);
	 Notification updateNotification (Notification Qz);
	  void DeleteNotification(long nt );
	  List<Notification> ShowNotification();
	  Notification sendnotif(Notification noif,long iduser);
	  List<Notification> usernotifs(long iduser);
	  

}
